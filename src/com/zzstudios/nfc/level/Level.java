package com.zzstudios.nfc.level;

import java.util.ArrayList;
import java.util.Random;

import com.zzstudios.nfc.assets.Asset;
import com.zzstudios.nfc.assets.Screen;
import com.zzstudios.nfc.entity.Entity;
import com.zzstudios.nfc.entity.Player;
import com.zzstudios.nfc.input.InputHandler;

public class Level {
	
	public ArrayList<Entity> entities = new ArrayList<Entity>();
	
	public int[] scenary = new int[128];
	
	public Player player = new Player();
	
	Random random = new Random();
	
	public Level(){
		for(int i = 0; i < scenary.length; i++){
			if(random.nextBoolean())scenary[i] = random.nextInt(Asset.tiles.width/16+1);
		}
	}
	
	public void tick(InputHandler input){
		player.tick(this, input);
	}
	
	public void render(Screen screen, InputHandler input){
		int xScroll = (int) (player.x-80);
		
		screen.fill(0, 0, screen.width, screen.height, 0);

		screen.fill(0, 128, screen.width, screen.height, 3);
		for(int i = -1; i < 11; i++){
			int xo = xScroll/16;
			int id = i+xo;
			while(id < 0)id += 128;
			id = scenary[id];
			if(id != 0){
				screen.draw(Asset.tiles, i*16-xScroll%16, 112, (id-1)*16, 0, 16, 16);
			}
		}
		
		player.render(screen, this, xScroll);
	}
}
