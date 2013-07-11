package com.dddbomber.nfc.assets;

public class Dither {
	public int[] matrix = {
			0, 0, 0,
			0, 0, 0,
			0, 0, 0
	};
	
	public static int[] translationMatrix = {
		4, 0, 2, 6, 8, 1, 3, 5, 7 
	};
	
	public Dither(int amount){
		this.createDither(amount);
	}

	private void createDither(int amount) {
		for(int i = 0; i < amount; i++){
			matrix[translationMatrix[i]] = 1;
		}
	}
	
	public void render(Screen screen, int x, int y, int baseCol){
		for(int xp = 0; xp < 3; xp++){
			int xd = x+xp;
			if(xd >= screen.width || xd < 0)continue;
			for(int yp = 0; yp < 3; yp++){
				int yd = y+yp;
				if(yd >= screen.height || yd < 0)continue;
				
				screen.pixels[xd + yd * screen.width] = Bitmap.cols[baseCol+matrix[xp+yp*3]];
			}
		}
	}

	public void showDither(){
		for(int y = 0; y < 3; y++){
			System.out.println();
			for(int x = 0; x < 3; x++){
				System.out.print(matrix[x+y*3] +" ");
			}
		}
	}
}
