package com.zzstudios.nfc.entity;

import java.awt.Rectangle;
import java.util.Random;

import com.zzstudios.nfc.assets.Screen;
import com.zzstudios.nfc.input.InputHandler;
import com.zzstudios.nfc.level.Level;

public class Entity {
	public double x, y, rotation;
	
	public int xSize, ySize;
	
	protected Random random = new Random();

	public boolean removed;
	
	public void tick(Level level, InputHandler input){
		
	}
	
	public void render(Screen screen, Level level, int xScroll){
		
	}

	public void damage(Level level, int i) {
		
	}
	
	public boolean intersects(Entity e) {
		Rectangle r = new Rectangle((int) x, (int) y, xSize, ySize);
		Rectangle er = new Rectangle((int) e.x, (int) e.y, e.xSize, e.ySize);
		return r.intersects(er);
	}
}
