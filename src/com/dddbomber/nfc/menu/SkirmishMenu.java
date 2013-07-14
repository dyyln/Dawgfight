package com.dddbomber.nfc.menu;

import com.dddbomber.nfc.assets.Screen;
import com.dddbomber.nfc.entity.*;
import com.dddbomber.nfc.input.InputHandler;
import com.dddbomber.nfc.level.Level;
import com.dddbomber.nfc.level.Skirmish;

public class SkirmishMenu extends Menu {
	public Level level;
	
	public SkirmishMenu(int totalUnits, int maxUnits, boolean turretsAllowed) {
		level = new Skirmish(totalUnits, maxUnits, turretsAllowed);
	}

	@Override
	public void tick(InputHandler input) {
		level.tick(input);
	}

	@Override
	public void render(Screen screen, InputHandler input) {
		level.render(screen, input);
	}

}
