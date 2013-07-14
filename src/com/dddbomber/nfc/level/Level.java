package com.dddbomber.nfc.level;

import java.util.ArrayList;
import java.util.Random;

import com.dddbomber.nfc.assets.Asset;
import com.dddbomber.nfc.assets.Bitmap;
import com.dddbomber.nfc.assets.Screen;
import com.dddbomber.nfc.entity.Enemy;
import com.dddbomber.nfc.entity.Entity;
import com.dddbomber.nfc.entity.Freindly;
import com.dddbomber.nfc.entity.Gun;
import com.dddbomber.nfc.entity.Hoop;
import com.dddbomber.nfc.entity.Player;
import com.dddbomber.nfc.entity.Target;
import com.dddbomber.nfc.input.InputHandler;

public class Level {
	
	public ArrayList<Entity> entities = new ArrayList<Entity>();

	public int[] scenary = new int[128];
	public int[] clouds = new int[32];
	
	public Player player = new Player();
	
	Random random = new Random();
	
	public int score = 0;

	public boolean complete;
	
	public int startDelay = 180;
	
	public Level(){
		for(int i = 0; i < scenary.length; i++){
			scenary[i] = random.nextInt(Asset.tiles.width/16+1);
		}
		for(int i = 0; i < clouds.length; i++){
			clouds[i] = random.nextInt(16);
		}
		entities.add(player);
		entities.add(new Enemy());
	}
	
	public int freindlies;
	
	public void tick(InputHandler input){
		if(startDelay > 0){
			startDelay--;
			return;
		}
		
		int left = 0;
		freindlies = 0;
		for(int i = 0; i < entities.size(); i++){
			Entity e = entities.get(i);
			e.tick(this, input);
			if(e.removed)entities.remove(i--);
			if(e instanceof Enemy || e instanceof Gun || e instanceof Hoop || e instanceof Target){
				left++;
			}
			if(e instanceof Freindly){
				freindlies++;
			}
		}
		if(left == 0)complete = true;
		if(random.nextInt(480)==0){
			int xScroll = (int) (player.x-80);
			int offset = random.nextInt(64);
			Enemy e = new Enemy();
			if(random.nextBoolean()){
				e.x = xScroll-16-offset;
			}else{
				e.x = xScroll+160+offset;
			}
			//entities.add(e);
		}
	}
	
	public Entity getClosestEnemy(Entity origin, double d) {
        Entity closestEnemy = null;
        double bestDistSquared = Double.MAX_VALUE;
        for (Entity e : entities) {
        	if(!(e instanceof Enemy) && !(e instanceof Gun))continue;
        	double xd = e.x-origin.x;
        	double yd = e.y-origin.y;
            double distSquared = xd*xd+yd*yd;
            if (distSquared < bestDistSquared && distSquared < d * d) {
                bestDistSquared = distSquared;
                closestEnemy = e;
            }
        }
        return closestEnemy;
    }


	public Entity getClosestFreindly(Entity origin, double d) {
        Entity closestEnemy = null;
        double bestDistSquared = Double.MAX_VALUE;
        for (Entity e : entities) {
        	if(!(e instanceof Freindly) && !(e instanceof Player))continue;
        	double xd = e.x-origin.x;
        	double yd = e.y-origin.y;
            double distSquared = xd*xd+yd*yd;
            if (distSquared < bestDistSquared && distSquared < d * d) {
                bestDistSquared = distSquared;
                closestEnemy = e;
            }
        }
        return closestEnemy;
	}
	
	public void render(Screen screen, InputHandler input){
		int xScroll = (int) (player.x-80);
		int yScroll = (int) (player.y-36);
		if(yScroll > 0)yScroll = 0;
		if(yScroll < -180)yScroll = -180;
		
		screen.fill(0, 0, screen.width, 144, 0);
		
		screen.draw(Asset.tiles, -(xScroll/3)%256-256, 100-yScroll/3, 0, 16, 256, 28);
		screen.draw(Asset.tiles, -(xScroll/3)%256, 100-yScroll/3, 0, 16, 256, 28);
		screen.draw(Asset.tiles, -(xScroll/3)%256+256, 100-yScroll/3, 0, 16, 256, 28);

		screen.draw(Asset.tiles, -(xScroll/2)%256-256, 108-yScroll/2, 0, 48, 256, 20);
		screen.draw(Asset.tiles, -(xScroll/2)%256, 108-yScroll/2, 0, 48, 256, 20);
		screen.draw(Asset.tiles, -(xScroll/2)%256+256, 108-yScroll/2, 0, 48, 256, 20);

		screen.fill(0, 128-yScroll, screen.width, 16, 3);

		for(int i = -1; i < (screen.height)/3+1; i++){
			for(int x = -1; x < screen.width/3+1; x++){
				int yo = (-yScroll/3)+i-48;
				if(yo < 0)continue;
				if((yo/2) >= 18){
					screen.fill(x*3-xScroll%3, screen.height-24-i*3-yScroll%3, 3, 3, 2);
					continue;
				}
				Bitmap.dithers.get((yo/2)%9).render(screen, x*3-xScroll%3, screen.height-24-i*3-yScroll%3, (yo/2)/9);
			}
		}
		
		for(int i = -1; i < 11; i++){
			int xo = (xScroll)/16;
			int id = i+xo;
			while(id < 0)id += 128;
			id = scenary[id%128];
			if(id != 0){
				screen.draw(Asset.tiles, i*16-(xScroll)%16, 112-yScroll, (id-1)*16, 0, 16, 16);
			}
		}
		
		for(Entity e : entities){
			e.render(screen, this, xScroll, yScroll);
		}

		for(int i = -1; i < 3; i++){
			int xo = (xScroll/96);
			int id = i+xo;
			while(id < 0)id += 32;
			id = clouds[id%32];
			if(id != 0){
				screen.draw(Asset.cloud, i*96-xScroll%96+8+id, 5+id*2-yScroll, 0, 0, 64, 16);
			}
		}
		
		screen.fill(0, 128, screen.width, 16, 3);
		
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
		if(startDelay > 0){
			screen.fill(0, 0, screen.width, screen.height, 3, 4);
			msg = ""+(startDelay/60+1);
			screen.fill(78-msg.length()*3, 22, 16, 20, 3);
			screen.draw(msg, 80-msg.length()*3, 24, 1, 2);
		}
	}
}
