package com.dddbomber.nfc.menu;

import com.dddbomber.nfc.assets.Screen;
import com.dddbomber.nfc.entity.*;
import com.dddbomber.nfc.input.InputHandler;
import com.dddbomber.nfc.level.Level;
import com.dddbomber.nfc.level.Skirmish;

public class SkirmishMenu extends Menu {
	public Level level = new Skirmish(100);
	
	@Override
	public void tick(InputHandler input) {
		level.tick(input);
	}

	@Override
	public void render(Screen screen, InputHandler input) {
		level.render(screen, input);
	}

}
