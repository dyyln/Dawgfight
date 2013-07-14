package com.dddbomber.nfc.entity;

import com.dddbomber.nfc.assets.Asset;
import com.dddbomber.nfc.assets.Screen;
import com.dddbomber.nfc.input.InputHandler;
import com.dddbomber.nfc.level.Level;

public class Hangar extends Entity {

	public Hangar(int xo){
		this.x = xo;
		this.y = 104;
		xSize  = 32;
		ySize = 24;
	}
	
	public int anim = -1;
	
	@Override
	public void damage(Level level, int i){
		anim = 15;
		level.entities.add(new ExplosionAir(x, y));
		xSize = ySize = 0;
	}
	
	public void tick(Level level, InputHandler input){
		if(anim > 0){
			anim--;
			if(anim == 0)removed = true;
			for(int i = 0; i< 3; i++){
				level.entities.add(new Particle(x+4, x+4, i+1));
				level.entities.add(new ExplosionAir(x-2, y-2));
			}
			return;
		}
		if(removed){
			level.score += 75;
		}
	}
	
	public void render(Screen screen, Level level, int xScroll, int yScroll){
		screen.draw(Asset.hangar, (int) x - xScroll, (int) y - yScroll, 0, 0, 32, 24);
	}
	
}
