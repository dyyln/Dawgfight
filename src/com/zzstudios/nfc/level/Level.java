package com.zzstudios.nfc.level;

import java.util.Random;

import com.zzstudios.nfc.assets.Asset;
import com.zzstudios.nfc.assets.Screen;
import com.zzstudios.nfc.input.InputHandler;

public class Level {
	public int[] scenary = new int[128];
	
	Random random = new Random();
	
	public Level(){
		for(int i = 0; i < scenary.length; i++){
			scenary[i] = random.nextInt(5);
		}
	}
	
	public void render(Screen screen, InputHandler input){
		screen.fill(0, 0, screen.width, screen.height, 0xFFFFDD, 100);

		screen.fill(0, 128, screen.width, screen.height, 0x4F4F22, 100);
		for(int i = 0; i < 10; i++){
			if(scenary[i]!=0){
				screen.draw(Asset.tiles, i*16, 112, (scenary[i]-1)*16, 0, 16, 16);
			}
		}
	}
}
