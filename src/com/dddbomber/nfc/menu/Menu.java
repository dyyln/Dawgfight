package com.dddbomber.nfc.menu;

import java.awt.event.KeyEvent;

import com.dddbomber.nfc.assets.Asset;
import com.dddbomber.nfc.assets.Bitmap;
import com.dddbomber.nfc.assets.Screen;
import com.dddbomber.nfc.input.InputHandler;

public class Menu {
	public static final int INTRO = 0, MAINMENU = 1, GAMEMENU = 2;
	
	private static int state = 0;
	
	public static void tick(InputHandler input){
		if(state == INTRO){
			tickIntro(input);
			tickIntro(input);
		}
	}
	
	private static void tickIntro(InputHandler input){
		if(introTicks < 360)introTicks++;
		if(input.keyboard.keys[KeyEvent.VK_SPACE])setupIntro();
	}
	
	public static void render(Screen screen){
		if(state == INTRO){
			renderIntro(screen);
		}
	}
	
	public static final String name = "MADE BY DDDBOMBER";
	
	private static void renderIntro(Screen screen){
		screen.fill(0, 0, screen.width, screen.height, 0);
		
		Bitmap b = new Bitmap(screen.width, screen.height);
		b.fill(0, 0, b.width, b.height, 0);
		b.draw(name, b.width/2-name.length()*3, screen.height-12, 3, 1);
		b.draw(Asset.avatar, 0, 0, 0, 0, 160, 128);
		
		int ticks = introTicks;
		if(ticks > 240)ticks = 240;
		
		Bitmap nb = new Bitmap(screen.width, screen.height);
		nb.fill(0, 0, nb.width, nb.height, 0);
		double scale = (double)ticks/240.0;
		int scaledHeight = (int) (screen.height*scale);
		nb.drawScaledAndCentered(b, screen.width/2, screen.height-scaledHeight/2, 0, 0, screen.width, screen.height, 1.0, scale);
		
		if(introTicks > 240){
			if(introTicks < 348){
				screen.drawDithered(nb, 0, 0, 0, 0, nb.width, nb.height, (introTicks-240)/12);
			}
		}else{
			screen.draw(nb, 0, 0, 0, 0, nb.width, nb.height);
		}
	}
	
	public static void setMenu(int menu){
		if(menu == INTRO){
			setupIntro();
		}else if(menu == MAINMENU){
			setupMainmenu();
		}else if(menu == GAMEMENU){
			setupGamemenu();
		}
	}
	
	private static int introFrame = 0, introTicks = 0, introTarget = 129;

	private static void setupIntro() {
		introFrame = introTicks = 0;
		introTarget = 129;
	}
	
	private static void setupMainmenu() {
		
	}
	
	private static void setupGamemenu() {
		
	}
}
