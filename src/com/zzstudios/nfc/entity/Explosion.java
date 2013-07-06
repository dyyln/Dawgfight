package com.zzstudios.nfc.entity;

import java.awt.event.KeyEvent;

import com.zzstudios.nfc.assets.Asset;
import com.zzstudios.nfc.assets.Screen;
import com.zzstudios.nfc.input.InputHandler;
import com.zzstudios.nfc.level.Level;

public class Explosion extends Entity{
	
	public Explosion(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	public int anim = 24;
	
	public void tick(Level level, InputHandler input){
		anim--;
		if(anim <= 0)removed = true;
	}
	
	public void render(Screen screen, Level level, int xScroll){
		screen.draw(Asset.exp_ground, (int) x - xScroll, (int) y, (anim/4)*16, 0, 16, 16);
	}
}
