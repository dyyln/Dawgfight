package com.zzstudios.nfc.entity;

import java.awt.event.KeyEvent;

import com.zzstudios.nfc.assets.Asset;
import com.zzstudios.nfc.assets.Screen;
import com.zzstudios.nfc.input.InputHandler;
import com.zzstudios.nfc.level.Level;

public class Missile extends Entity{
	
	public Missile(double x, double y, double rotation){
		xSize = 2;
		ySize = 2;
		this.x = x;
		this.y = y;
		this.rotation = rotation;
	}
	
	public double speed = 2.0;
	
	public void tick(Level level, InputHandler input){

		if(rotation < 179)rotation += 0.1;
		if(rotation > 181)rotation -= 0.1;
		
		double movementAngle = Math.toRadians(rotation);
		double xMove = (Math.sin(movementAngle) * speed);
		double yMove = (Math.cos(movementAngle) * speed);
		
		x += xMove;
		y -= yMove;
		
		if(y > 124){
			removed = true;
			level.entities.add(new Particle(x, y, 2));
			level.entities.add(new Particle(x, y, 2));
			level.entities.add(new Particle(x, y, 2));
		}
	}
	
	public void render(Screen screen, Level level, int xScroll){
		screen.drawRotated(Asset.bullet, (int) x - xScroll, (int) y, 0, 0, 4, 4, (int) -rotation);
	}
}
