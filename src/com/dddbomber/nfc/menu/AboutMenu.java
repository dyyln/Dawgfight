package com.dddbomber.nfc.menu;

import com.dddbomber.nfc.assets.Asset;
import com.dddbomber.nfc.assets.Screen;
import com.dddbomber.nfc.input.InputHandler;

public class AboutMenu extends Menu {

	public int ticks = 0;
	public boolean back = false;
	
	@Override
	public void tick(InputHandler input) {
		if(back){
			if(ticks > 0){
				ticks--;
			}else{
				MainMenu mm = new MainMenu();
				mm.menuTicks = 32;
				Menu.menu = mm;
			}
		}else{
			if(ticks < 96){
				ticks++;
			}else{
				for(boolean b : input.keyboard.keys){
					if(b){
						back = true;
					}
				}
			}
		}

	}

	@Override
	public void render(Screen screen, InputHandler input) {
		screen.fill(0, 0, screen.width, screen.height, 0);
		screen.draw(Asset.title, screen.width/2-64+ticks*2, -22, 0, 0, 128, 96);

		for(int i = 0; i < 3; i++){
			String s = MainMenu.options[i];
			screen.draw(s, screen.width/2-s.length()*3+ticks*2, 96+i*12+32*2, 3, 1);
		}
		
		String msg = "BY DDDBOMBER";
		screen.draw(msg, screen.width/2-msg.length()*3+ticks*2-192, 16, 3, 1);
		msg = "MADE FOR THE GBJAM";
		screen.draw(msg, screen.width/2-msg.length()*3+ticks*2-192, 32, 3, 1);
		msg = "ONE GAME A MONTH";
		screen.draw(msg, screen.width/2-msg.length()*3+ticks*2-192, 48, 3, 1);
		msg = "NO FUTURE CONTEST";
		screen.draw(msg, screen.width/2-msg.length()*3+ticks*2-192, 64, 3, 1);
		msg = "IN THE WEEK OF";
		screen.draw(msg, screen.width/2-msg.length()*3+ticks*2-192, 80, 3, 1);
		msg = "8TH JULY 2013";
		screen.draw(msg, screen.width/2-msg.length()*3+ticks*2-192, 96, 3, 1);
		
		msg = "PRESS ANY BUTTON TO RETURN";
		screen.draw(msg, screen.width/2-msg.length()*3+ticks*2-192, 116, 3, 1);
	}

}
