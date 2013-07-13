package com.dddbomber.nfc.menu;

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
			missions[i].complete[i%3] = true;
		}
	}

	@Override
	public void tick(InputHandler input) {

	}

	@Override
	public void render(Screen screen) {
		screen.fill(0, 0, screen.width, screen.height, 0);

		for(int x = 0; x < 4; x++){
			for(int y = 0; y < 3; y++){
				Mission m = missions[x+y*4];
				int xo = 0;
				if(selected == x+y*4)xo = 32;
				screen.draw(Asset.mission, x*36+8, y*28+8, 0, 0, 32, 24);
				if(m.complete[0])screen.draw(Asset.mission, x*36+13, y*28+23, 5, 39, 5, 5);
				if(m.complete[1])screen.draw(Asset.mission, x*36+21, y*28+22, 13, 38, 6, 6);
				if(m.complete[2])screen.draw(Asset.mission, x*36+30, y*28+23, 22, 39, 5, 5);
				String msg = "" +(x+y*4+1);
				screen.draw(msg, x*36+24-msg.length()*3, y*28+12, 3, 1);
			}
		}
	}

}
