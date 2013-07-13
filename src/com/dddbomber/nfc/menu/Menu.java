package com.dddbomber.nfc.menu;

import com.dddbomber.nfc.assets.Screen;
import com.dddbomber.nfc.input.InputHandler;

public abstract class Menu {
	public static Menu menu = new IntroMenu();
	
	public abstract void tick(InputHandler input);
	
	public abstract void render(Screen screen);
}
