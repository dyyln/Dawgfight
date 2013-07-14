package com.dddbomber.nfc.level;

import com.dddbomber.nfc.assets.Asset;
import com.dddbomber.nfc.assets.Screen;
import com.dddbomber.nfc.entity.Enemy;
import com.dddbomber.nfc.entity.Entity;
import com.dddbomber.nfc.entity.Freindly;
import com.dddbomber.nfc.entity.FreindlyGun;
import com.dddbomber.nfc.entity.Gun;
import com.dddbomber.nfc.entity.Hoop;
import com.dddbomber.nfc.entity.Player;
import com.dddbomber.nfc.entity.Target;
import com.dddbomber.nfc.input.InputHandler;

public class Skirmish extends Level {

	public int playerTeam, enemyTeam;
	
	public Skirmish(int teamSize) {
		this.playerTeam = this.enemyTeam = teamSize;
		entities.add(new FreindlyGun());
		entities.add(new Gun());
		entities.add(new FreindlyGun());
		entities.add(new Gun());
		entities.add(new FreindlyGun());
		entities.add(new Gun());
		entities.add(new FreindlyGun());
		entities.add(new Gun());
		entities.add(new FreindlyGun());
		entities.add(new Gun());
		entities.add(new FreindlyGun());
		entities.add(new Gun());
	}
	
	public void tick(InputHandler input){
		if(startDelay > 0){
			startDelay--;
		}else{
			if(!entities.contains(player))entities.add(player);
		}
		
		int players = 0, enemies = 0;
		for(int i = 0; i < entities.size(); i++){
			Entity e = entities.get(i);
			e.tick(this, input);
			if(e.removed)entities.remove(i--);
			if(e instanceof Enemy){
				enemies++;
			}
			if(e instanceof Freindly){
				players++;
			}
		}
		
		if(player.removed && playerTeam > 0){
			playerTeam--;
			player = new Player();
			startDelay = 180;
		}

		if(random.nextInt(120)==0){
			if(enemyTeam > 0 && enemies < 10){
				int xScroll = (int) (player.x-80);
				int offset = random.nextInt(64);
				Enemy e = new Enemy();
				if(random.nextBoolean()){
					e.x = xScroll-16-offset;
				}else{
					e.x = xScroll+160+offset;
				}
				enemyTeam--;
				entities.add(e);
			}
			if(playerTeam > 0 && players < 10){
				int xScroll = (int) (player.x-80);
				int offset = random.nextInt(64);
				Freindly e = new Freindly();
				if(random.nextBoolean()){
					e.x = xScroll-16-offset;
				}else{
					e.x = xScroll+160+offset;
				}
				playerTeam--;
				entities.add(e);
			}
		}

		if(random.nextInt(240)==0){
			if(enemyTeam > 1 && enemies < 10){
				enemyTeam-=2;
				entities.add(new Gun());
			}
			if(playerTeam > 1 && players < 10){
				playerTeam-=2;
				entities.add(new FreindlyGun());
			}
		}
	}

	public void render(Screen screen, InputHandler input){
		super.render(screen, input);
		screen.fill(0, 128, screen.width, 16, 3);

		String msg = "" +playerTeam;
		int toAdd = 3-msg.length();
		for(int i = 0; i < toAdd; i++){
			msg = "0" +msg;
		}
		screen.draw(Asset.score, 0, 130, 0, 0, 53, 13);
		screen.draw(msg, 3, 133, 0, 1);
		
		msg = "" +enemyTeam;
		toAdd = 3-msg.length();
		for(int i = 0; i < toAdd; i++){
			msg = "0" +msg;
		}
		screen.draw(Asset.score, screen.width-53, 130, 0, 0, 53, 13);
		screen.draw(msg, screen.width-50, 133, 0, 1);
	}

}
