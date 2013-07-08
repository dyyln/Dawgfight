package com.dddbomber.nfc.entity;

import com.dddbomber.nfc.assets.Asset;
import com.dddbomber.nfc.assets.Screen;
import com.dddbomber.nfc.input.InputHandler;
import com.dddbomber.nfc.level.Level;

public class ExplosionAir extends Entity{
	
	public ExplosionAir(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	public int anim = 0;
	
	public void tick(Level level, InputHandler input){
		anim++;
		if(anim >= 24)removed = true;
	}
	
	public void render(Screen screen, Level level, int xScroll, int yScroll){
		screen.draw(Asset.exp_air, (int) x - xScroll, (int) y - yScroll, (anim/4)*16, 0, 16, 16);
	}
}
