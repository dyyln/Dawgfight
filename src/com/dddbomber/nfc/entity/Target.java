package com.dddbomber.nfc.entity;

import com.dddbomber.nfc.assets.Asset;
import com.dddbomber.nfc.assets.Screen;
import com.dddbomber.nfc.input.InputHandler;
import com.dddbomber.nfc.level.Level;

public class Target extends Entity {

	public Target(int xo){
		this.x = xo;
		this.y = random.nextInt(96);
		xSize = ySize = 8;
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
		if(anim >= 0){
			screen.draw(Asset.targets, (int) x - xScroll - 4, (int) y - yScroll - 4, 16, ((15-anim)/5)*16, 16, 16);
		}else{
			screen.draw(Asset.targets, (int) x - xScroll - 4, (int) y - yScroll - 4, 16, 0, 16, 16);
		}
	}
	
}
