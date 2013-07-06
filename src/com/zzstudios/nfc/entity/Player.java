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
	
	public double speed = 0.0;
	
	public void tick(Level level, InputHandler input){
		boolean left = input.keyboard.keys[KeyEvent.VK_LEFT],
				right = input.keyboard.keys[KeyEvent.VK_RIGHT];
		if(left)rotation-=3;
		if(right)rotation+=3;
		
		if(speed < 1.0)speed += 0.01;
		if((left || right) && speed > 0.75)speed -= 0.02;
		
		double movementAngle = Math.toRadians(rotation);
		double xMove = (Math.sin(movementAngle) * speed);
		double yMove = (Math.cos(movementAngle) * speed);
		
		speed -= yMove/100;
		
		x += xMove;
		y -= yMove;
	}
	
	public void render(Screen screen, Level level, int xScroll){
		screen.drawRotated(Asset.plane, (int) x, (int) y, 0, 0, 16, 16, (int) -rotation);
	}
}
