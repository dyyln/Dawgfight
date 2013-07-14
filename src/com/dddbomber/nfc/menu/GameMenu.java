package com.dddbomber.nfc.menu;

import com.dddbomber.nfc.assets.Screen;
import com.dddbomber.nfc.entity.*;
import com.dddbomber.nfc.input.InputHandler;
import com.dddbomber.nfc.level.Level;

public class GameMenu extends Menu {

	public Mission mission;
	public int id;
	public Level level = new Level();
	
	public boolean timerAttached = false;
	public int timer = 0;
	
	public GameMenu(Mission mission, int id){
		this.id = id;
		this.mission = mission;
		for(MissionType t : mission.type){
			if(t == MissionType.cleared){
				level.entities.clear();
				level.entities.add(level.player);
			}
			if(t == MissionType.enemy){
				level.entities.add(new Enemy());
			}
			if(t == MissionType.freindly){
				level.entities.add(new Freindly());
			}
			if(t == MissionType.aagun){
				level.entities.add(new Gun());
			}
			if(t == MissionType.aagunf){
				level.entities.add(new FreindlyGun());
			}
			if(t == MissionType.hoops){
				for(int i = 0; i < 12; i++){
					level.entities.add(new Hoop(i*48+160));
				}
			}
			if(t == MissionType.targets){
				for(int i = 0; i < 25; i++){
					level.entities.add(new Target(i*24+160));
				}
			}
			if(t == MissionType.timed){
				timerAttached = true;
			}
		}
	}
	
	@Override
	public void tick(InputHandler input) {
		level.tick(input);
		if(timerAttached){
			timer++;
		}
		if(level.player.removed){
			Menu.menu = PlayMenu.instance;
		}
		if(level.complete){
			mission.complete[1] = true;
			Menu.menu = PlayMenu.instance;
			if(timerAttached){
				int seconds = timer/60;
				System.out.println(seconds);
				if(id <= 1){
					if(seconds < 30)mission.complete[0] = true;
					if(seconds < 20)mission.complete[2] = true; 
				}
			}
			if(id == 2 || id == 3 || id == 7){
				if(level.player.health > 10)mission.complete[0] = true;
				if(level.player.health > 15)mission.complete[2] = true;
			}
			if(id == 4 || id == 5 || id == 6){
				if(level.freindlies > 0)mission.complete[0] = true;
				if(level.freindlies > 1)mission.complete[2] = true;
			}
		}
	}

	@Override
	public void render(Screen screen, InputHandler input) {
		level.render(screen, input);
	}

}
