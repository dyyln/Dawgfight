package com.dddbomber.nfc.menu;

import java.awt.event.KeyEvent;

import kuusisto.tinysound.TinySound;

import com.dddbomber.nfc.assets.Asset;
import com.dddbomber.nfc.assets.Screen;
import com.dddbomber.nfc.input.InputHandler;
import com.dddbomber.nfc.sound.SoundPlayer;

public class SkirmishSetupMenu extends Menu {

	public int maxUnits = 5, totalUnits = 50;
	
	public int ticks = 0, selected, moveDelay;
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
				return;
			}

			if(moveDelay > 0){
				moveDelay--;
				return;
			}
			if(input.keyboard.keys[KeyEvent.VK_W] || input.keyboard.keys[KeyEvent.VK_UP]){
				if(selected > 0)selected--;
				moveDelay = 20;
			}
			if(input.keyboard.keys[KeyEvent.VK_S] || input.keyboard.keys[KeyEvent.VK_DOWN]){
				if(selected < 3)selected++;
				moveDelay = 20;
			}
			
			if(input.keyboard.keys[KeyEvent.VK_SPACE]){
				
			}
		}

	}

	public static void clearProgress() {
		
	}

	@Override
	public void render(Screen screen, InputHandler input) {
		screen.fill(0, 0, screen.width, screen.height, 0);
		screen.draw(Asset.title, screen.width/2-64+ticks*2, -22, 0, 0, 128, 96);

		for(int i = 0; i < 3; i++){
			String s = MainMenu.options[i];
			screen.draw(s, screen.width/2-s.length()*3+ticks*2, 96+i*12+32*2, 3, 1);
		}
		
		String msg = "OPTIONS";
		screen.draw(msg, screen.width/2-msg.length()*3+ticks*2-192, 8, 3, 1);

		msg = "TEAMSIZE";
		screen.draw(msg, screen.width/2-msg.length()*3+ticks*2-192, 32, 3, 1);
		msg = ""+totalUnits;
		if(selected == 0)msg = "> " +msg +"< "; 
		screen.draw(msg, screen.width/2-msg.length()*3+ticks*2-192, 40, 3, 1);

		msg = "MAX UNITS AT ONCE";
		screen.draw(msg, screen.width/2-msg.length()*3+ticks*2-192, 56, 3, 1);
		msg = ""+maxUnits;
		if(selected == 1)msg = "> " +msg +"< "; 
		screen.draw(msg, screen.width/2-msg.length()*3+ticks*2-192, 72, 3, 1);
		

		msg = "RETURN";
		if(selected == 2)msg = "> " +msg +"< ";
		screen.draw(msg, screen.width/2-msg.length()*3+ticks*2-192, 116, 3, 1);
	}

}
