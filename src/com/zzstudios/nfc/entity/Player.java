package com.zzstudios.nfc.entity;

import java.awt.event.KeyEvent;

import com.zzstudios.nfc.assets.Asset;
import com.zzstudios.nfc.assets.Screen;
import com.zzstudios.nfc.input.InputHandler;
import com.zzstudios.nfc.level.Level;

public class Player extends Entity{
	
	public Player(){
		xSize = 8;
		ySize = 8;
		x = 80;
		y = 80;
		rotation = 0;
	}
	
	public void tick(Level level, InputHandler input){
		if(input.keyboard.keys[KeyEvent.VK_LEFT])rotation-=3;
		if(input.keyboard.keys[KeyEvent.VK_RIGHT])rotation+=3;
		
		double movementAngle = Math.toRadians(rotation);
		double xMove = (Math.sin(movementAngle) * 1.0);
		double yMove = (Math.cos(movementAngle) * 1.0);
		
		x += xMove;
		y -= yMove;
	}
	
	public void render(Screen screen, Level level, int xScroll){
		screen.drawRotated(Asset.plane, (int) x, (int) y, 0, 0, 16, 16, (int) -rotation);
	}
}
