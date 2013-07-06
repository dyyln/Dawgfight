package com.zzstudios.nfc.entity;

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
}
