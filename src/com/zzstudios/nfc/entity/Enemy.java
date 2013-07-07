package com.zzstudios.nfc.entity;

import java.awt.event.KeyEvent;

import com.zzstudios.nfc.assets.Asset;
import com.zzstudios.nfc.assets.Screen;
import com.zzstudios.nfc.input.InputHandler;
import com.zzstudios.nfc.level.Level;

public class Enemy extends Entity{
	
	public Enemy(){
		xSize = 8;
		ySize = 8;
		x = 80;
		y = 76;
		rotation = 90;
	}
	
	public double speed = 0.0;
	public int shootDelay;
	public int missiles = 99;
	
	public void tick(Level level, InputHandler input){
		
		double prev = rotation;
		rotation = Asset.lerpDegrees(rotation, Math.toDegrees(Math.atan2(level.player.y-y, level.player.x-x)), 0.5);
		
		
		//if((left || right) && speed > 0.75)speed -= 0.02;
		
		double movementAngle = Math.toRadians(rotation);
		double xMove = (Math.sin(movementAngle) * speed);
		double yMove = (Math.cos(movementAngle) * speed);
		
		if(shootDelay-- <= 0 && input.keyboard.keys[KeyEvent.VK_SPACE]){
			level.entities.add(new Bullet(x+4, y+4, rotation));
			shootDelay = 10;

			//level.entities.add(new ExplosionAir(x, y));
		}
			
		
		speed -= yMove/100;
		
		x += xMove;
		y -= yMove;
		
		if(speed < 1.0)speed += 0.01;
	}
	
	public void render(Screen screen, Level level, int xScroll){
		if(rotation == 90){
			screen.draw(Asset.plane, (int) x - xScroll, (int) y, 16, 0, 16, 16);
		}else if(rotation == 270){
			screen.draw(Asset.plane, (int) x - xScroll, (int) y, 32, 0, 16, 16);
		}else{
			screen.drawRotated(Asset.plane, (int) x - xScroll, (int) y, (rotation > 180 ? 64 : 48), 0, 16, 16, (int) -rotation);
		}
	}
}
