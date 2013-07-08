package com.dddbomber.nfc.entity;

import java.awt.event.KeyEvent;

import com.dddbomber.nfc.assets.Asset;
import com.dddbomber.nfc.assets.Screen;
import com.dddbomber.nfc.input.InputHandler;
import com.dddbomber.nfc.level.Level;

public class Particle extends Entity{
	
	public int col;
	
	public Particle(double x, double y, int col){
		this.x = x;
		this.y = y-2;
		this.col = col;
	}
	
	public double xSpeed = (random.nextInt(41)-20)*0.1, ySpeed = -2.0-(random.nextInt(10)*0.1);
	
	public Particle(double x, double y, int col, double xSpeed, double ySpeed){
		this.x = x;
		this.y = y-2;
		this.col = col;
		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
	}
	
	public void tick(Level level, InputHandler input){
		
		if(xSpeed > 0.1){
			xSpeed -= 0.05;
		}else if(xSpeed < -0.1){
			xSpeed += 0.05;
		}else{
			xSpeed = 0;
		}
		
		if(ySpeed < 1.0){
			ySpeed += 0.1;
		}else{
			ySpeed = 1.0;
		}
		
		x += xSpeed;
		y += ySpeed;
		
		if(y > 124)removed = true;
	}
	
	public void render(Screen screen, Level level, int xScroll){
		screen.fill((int) x - xScroll, (int) y, 1, 1, col);
	}
}
