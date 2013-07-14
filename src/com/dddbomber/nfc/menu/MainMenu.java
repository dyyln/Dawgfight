package com.dddbomber.nfc.menu;

import java.awt.event.KeyEvent;

import com.dddbomber.nfc.assets.Asset;
import com.dddbomber.nfc.assets.Screen;
import com.dddbomber.nfc.input.InputHandler;

public class MainMenu extends Menu {
	
	public  int menuTicks = 0, selected = 0, anim, moveDelay = 60;

	private static String[] options = {"PLAY", "OPTIONS", "ABOUT"};

	@Override
	public void tick(InputHandler input) {
		if(menuTicks < 32){
			menuTicks++;
		}
		if(anim < 60){
			anim++;
		}else{
			anim = 0;
		}
		if(moveDelay > 0){
			moveDelay--;
			return;
		}
		if(input.keyboard.keys[KeyEvent.VK_W]){
			if(selected > 0)selected--;
			moveDelay = 20;
		}
		if(input.keyboard.keys[KeyEvent.VK_S]){
			if(selected < 2)selected++;
			moveDelay = 20;
		}
		if(input.keyboard.keys[KeyEvent.VK_SPACE]){
			if(selected == 0)Menu.menu = PlayMenu.instance;
			if(selected == 1)Menu.menu = new OptionsMenu();
			if(selected == 2)Menu.menu = new AboutMenu();
		}
	}

	@Override
	public void render(Screen screen, InputHandler input) {
		screen.fill(0, 0, screen.width, screen.height, 0);
		screen.draw(Asset.title, screen.width/2-64, 10-menuTicks, 0, 0, 128, 96);
		
		for(int i = 0; i < 3; i++){
			String s = options[i];
			if(i == selected){
				if(anim < 40)s = "> " +s +" <";
			}
			screen.draw(s, screen.width/2-s.length()*3, 96+i*12+64-menuTicks*2, 3, 1);
		}
	}

}
