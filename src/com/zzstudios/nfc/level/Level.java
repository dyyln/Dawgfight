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
	public int[] clouds = new int[32];
	
	public Player player = new Player();
	
	Random random = new Random();
	
	public int score = 0;
	
	public Level(){
		for(int i = 0; i < scenary.length; i++){
			scenary[i] = random.nextInt(Asset.tiles.width/16+1);
		}
		for(int i = 0; i < clouds.length; i++){
			clouds[i] = random.nextInt(16);
		}
		entities.add(player);
	}
	
	public void tick(InputHandler input){
		for(int i = 0; i < entities.size(); i++){
			Entity e = entities.get(i);
			e.tick(this, input);
			if(e.removed)entities.remove(i--);
		}
	}
	
	public void render(Screen screen, InputHandler input){
		int xScroll = (int) (player.x-80);
		
		screen.fill(0, 0, screen.width, 128, 0);

		screen.fill(0, 128, screen.width, 16, 3);
		
		for(int i = -1; i < 11; i++){
			int xo = xScroll/16;
			int id = i+xo;
			while(id < 0)id += 128;
			id = scenary[id%128];
			if(id != 0){
				screen.draw(Asset.tiles, i*16-xScroll%16, 112, (id-1)*16, 0, 16, 16);
			}
		}
		
		for(Entity e : entities){
			e.render(screen, this, xScroll);
		}
		
		String msg = "" +score;
		int toAdd = 8-msg.length();
		for(int i = 0; i < toAdd; i++){
			msg = "0" +msg;
		}
		screen.draw(Asset.score, 0, 130, 0, 0, 53, 13);
		screen.draw(msg, 3, 133, 0, 1);
		
		screen.draw(Asset.missile, 124, 130, 0, 0, 13, 13);
		msg = "*"+player.missiles;
		screen.draw(msg, 140, 133, 0, 1);
		
		if(player.y == 116){
			msg = "PRESS UP TO TAKE OFF";
			screen.draw(msg, 80-msg.length()*3, 2, 3, 1);
		}

		
		for(int i = -1; i < 3; i++){
			int xo = xScroll/96;
			int id = i+xo;
			while(id < 0)id += 32;
			id = clouds[id%32];
			if(id != 0){
				screen.draw(Asset.cloud, i*96-xScroll%96+8+id, 5+id*2, 0, 0, 64, 16);
			}
		}
	}
}
