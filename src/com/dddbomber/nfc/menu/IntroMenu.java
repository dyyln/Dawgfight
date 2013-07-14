package com.dddbomber.nfc.menu;

import com.dddbomber.nfc.assets.Asset;
import com.dddbomber.nfc.assets.Bitmap;
import com.dddbomber.nfc.assets.Screen;
import com.dddbomber.nfc.input.InputHandler;

public class IntroMenu extends Menu {

	public int introTicks = 0;
	public boolean introDone = false;
	
	@Override
	public void tick(InputHandler input) {
		if(introTicks < 580){
			introTicks+=2;
		}else{
			introTicks = 520;
		}
		if(introTicks >= 520){
			for(boolean b : input.keyboard.keys){
				if(b){
					Menu.menu = new MainMenu();
				}
			}
		}
	}
	
	public static final String name = "MADE BY DDDBOMBER";
	public static final String altName = "DYLAN DHOKIA";

	@Override
	public void render(Screen screen, InputHandler input) {
		screen.fill(0, 0, screen.width, screen.height, 0);
		
		Bitmap b = new Bitmap(screen.width, screen.height);
		b.fill(0, 0, b.width, b.height, 0);
		b.draw(name, b.width/2-name.length()*3, screen.height-16, 3, 1);
		b.draw(altName, b.width/2-altName.length()*3, screen.height-8, 3, 1);
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
			}else if(introTicks > 360){
				int yo = introTicks;
				if(yo > 520)yo = 520;
				screen.draw(Asset.title, screen.width/2-64, (yo-510), 0, 0, 128, 96);
				if(introTicks >= 520){
					String msg = "PRESS ANY BUTTON TO BEGIN";
					if(introTicks % 60 <= 40)screen.draw(msg, screen.width/2-msg.length()*3, 128, 3, 1);
					introDone = true;
				}
			}
		}else{
			screen.draw(nb, 0, 0, 0, 0, nb.width, nb.height);
		}
	}

}
