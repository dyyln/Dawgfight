package com.dddbomber.nfc.entity;

import com.dddbomber.nfc.assets.Asset;
import com.dddbomber.nfc.assets.Screen;
import com.dddbomber.nfc.input.InputHandler;
import com.dddbomber.nfc.level.Level;
import com.dddbomber.nfc.sound.SoundPlayer;

public class Hoop extends Entity {

	public Hoop(int xo){
		this.x = xo;
		this.y = random.nextInt(96);
	}
	
	public int anim = -1;
	
	public void tick(Level level, InputHandler input){
		if(anim > 0){
			anim--;
			if(anim == 0){
				removed = true;
				SoundPlayer.ring.play(0.75);
			}
			return;
		}
		xSize = 16;
		ySize = 16;
		if(this.intersects(level.player)){
			anim = 15;
			level.score += 75;
		}
		xSize = 0;
		ySize = 0;
	}
	
	public void render(Screen screen, Level level, int xScroll, int yScroll){
		if(anim >= 0){
			screen.draw(Asset.targets, (int) x - xScroll, (int) y - yScroll, 0, ((15-anim)/5)*16, 16, 16);
		}else{
			screen.draw(Asset.targets, (int) x - xScroll, (int) y - yScroll, 0, 0, 16, 16);
		}
	}
	
}
