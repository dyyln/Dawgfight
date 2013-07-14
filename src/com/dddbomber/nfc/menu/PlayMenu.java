package com.dddbomber.nfc.menu;

import java.awt.event.KeyEvent;

import com.dddbomber.nfc.assets.Asset;
import com.dddbomber.nfc.assets.Screen;
import com.dddbomber.nfc.input.InputHandler;

public class PlayMenu extends Menu {
	
	public Mission[] missions = new Mission[12];
	
	public static PlayMenu instance = new PlayMenu();
	
	public int selected = 0;
	
	public PlayMenu(){
		for(int i = 0; i < missions.length; i++){
			missions[i] = new Mission();
		}
		missions[0].name = "Flight Training";
		missions[1].name = "Target Practice";
		missions[2].name = "Dogfight";
		missions[3].name = "Anti Anti Aircraft";
		missions[4].name = "Sqaud Fight";
		missions[5].name = "Enemy Territory";
		missions[6].name = "Air To Ground";
		missions[7].name = "Double Team";
		missions[8].name = "Ground Back up";
		missions[9].name = "Triple Team";
		missions[10].name = "Four On One";
		missions[11].name = "CUSTOM SKIRMISH";

		missions[0].type.add(MissionType.cleared);
		missions[0].type.add(MissionType.hoops);
		missions[0].type.add(MissionType.timed);
		
		missions[1].type.add(MissionType.cleared);
		missions[1].type.add(MissionType.targets);
		missions[1].type.add(MissionType.timed);

		missions[3].type.add(MissionType.cleared);
		missions[3].type.add(MissionType.aagun);

		missions[4].type.add(MissionType.enemy);
		missions[4].type.add(MissionType.enemy);
		missions[4].type.add(MissionType.freindly);
		missions[4].type.add(MissionType.freindly);

		missions[5].type.add(MissionType.aagun);
		missions[5].type.add(MissionType.enemy);
		missions[5].type.add(MissionType.freindly);
		missions[5].type.add(MissionType.freindly);

		missions[6].type.add(MissionType.cleared);
		missions[6].type.add(MissionType.aagun);
		missions[6].type.add(MissionType.aagun);
		missions[6].type.add(MissionType.aagun);
		missions[6].type.add(MissionType.freindly);
		missions[6].type.add(MissionType.freindly);

		missions[7].type.add(MissionType.enemy);

		missions[8].type.add(MissionType.enemy);
		missions[8].type.add(MissionType.enemy);
		missions[8].type.add(MissionType.enemy);
		missions[8].type.add(MissionType.enemy);
		missions[8].type.add(MissionType.aagunf);

		missions[9].type.add(MissionType.enemy);
		missions[9].type.add(MissionType.enemy);

		missions[10].type.add(MissionType.enemy);
		missions[10].type.add(MissionType.enemy);
		missions[10].type.add(MissionType.enemy);

	}

	public int moveDelay = 15;
	
	@Override
	public void tick(InputHandler input) {
		if(moveDelay > 0){
			moveDelay--;
			return;
		}
		int s = selected;
		if(input.keyboard.keys[KeyEvent.VK_W] || input.keyboard.keys[KeyEvent.VK_UP] && selected > 3)selected -= 4;
		if(input.keyboard.keys[KeyEvent.VK_S] || input.keyboard.keys[KeyEvent.VK_DOWN] && selected < 8)selected += 4;
		if(input.keyboard.keys[KeyEvent.VK_A] || input.keyboard.keys[KeyEvent.VK_LEFT] && selected > 0)selected --;
		if(input.keyboard.keys[KeyEvent.VK_D] || input.keyboard.keys[KeyEvent.VK_RIGHT] && selected < 11)selected ++;
		if(input.keyboard.keys[KeyEvent.VK_SPACE]){
			Menu.menu = new GameMenu(missions[selected], selected);
			if(selected == 11){
				Menu.menu = new SkirmishSetupMenu();
			}
			moveDelay = 30;
		}
		if(s != selected)moveDelay = 15;
	}

	@Override
	public void render(Screen screen, InputHandler input) {
		screen.fill(0, 0, screen.width, screen.height, 0);

		for(int x = 0; x < 4; x++){
			for(int y = 0; y < 3; y++){
				Mission m = missions[x+y*4];
				int xo = 0;
				if(selected == x+y*4)xo = 32;
				screen.draw(Asset.mission, x*36+8, y*28+8, xo, 0, 32, 24);
				if(m.complete[0])screen.draw(Asset.mission, x*36+13, y*28+23, 5, 39, 5, 5);
				if(m.complete[1])screen.draw(Asset.mission, x*36+21, y*28+22, 13, 38, 6, 6);
				if(m.complete[2])screen.draw(Asset.mission, x*36+30, y*28+23, 22, 39, 5, 5);
				String msg = "" +(x+y*4+1);
				if((x+y*4+1)==12)msg = "OWN";
				screen.draw(msg, x*36+24-msg.length()*3, y*28+12, 3, 1);
			}
		}
		
		String msg = missions[selected].name;
		screen.draw(msg, screen.width/2-msg.length()*3, 96, 3, 1);
	}

}
