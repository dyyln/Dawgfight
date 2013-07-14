package com.dddbomber.nfc.menu;

import java.awt.event.KeyEvent;

import kuusisto.tinysound.TinySound;

import com.dddbomber.nfc.assets.Asset;
import com.dddbomber.nfc.assets.Screen;
import com.dddbomber.nfc.input.InputHandler;
import com.dddbomber.nfc.sound.SoundPlayer;

public class OptionsMenu extends Menu {

	public static boolean sounds = true, music = true;
	
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
				if(selected == 3)back = true;
				if(selected == 2){
					music = !music;
					if(music){
						SoundPlayer.music.play(true, 0.25);
					}else{
						SoundPlayer.music.stop();
					}
				}
				if(selected == 1)sounds = !sounds;
				if(selected == 0)clearProgress();
				if(sounds){
					TinySound.setGlobalVolume(1.0);
				}else{
					TinySound.setGlobalVolume(0.0);
				}
				moveDelay = 20;
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

		msg = "CLEAR PROGRESS";
		if(selected == 0)msg = "> " +msg +"< "; 
		screen.draw(msg, screen.width/2-msg.length()*3+ticks*2-192, 32, 3, 1);
		msg = "SOUNDS : " +(sounds ? "ON" : "OFF");
		if(selected == 1)msg = "> " +msg +"< ";
		screen.draw(msg, screen.width/2-msg.length()*3+ticks*2-192, 48, 3, 1);
		msg = "MUSIC : " +(music ? "ON" : "OFF");
		if(selected == 2)msg = "> " +msg +"< ";
		screen.draw(msg, screen.width/2-msg.length()*3+ticks*2-192, 64, 3, 1);

		msg = "RETURN";
		if(selected == 3)msg = "> " +msg +"< ";
		screen.draw(msg, screen.width/2-msg.length()*3+ticks*2-192, 116, 3, 1);
	}

}
