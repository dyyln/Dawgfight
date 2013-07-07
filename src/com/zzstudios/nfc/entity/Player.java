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
		y = 116;
		rotation = 90;
	}
	
	public void damage(Level level, int i) {
		health--;
		level.entities.add(new Particle(x+6, y+6, 2));
		level.entities.add(new Particle(x+6, y+6, 2));
		level.entities.add(new Particle(x+6, y+6, 2));
		level.entities.add(new Particle(x+6, y+6, 2));
		level.entities.add(new Particle(x+6, y+6, 2));
		if(health == 0){
			level.entities.add(new ExplosionAir(x, y));
			removed = true;
		}
	}
	
	public double speed = 0.0;
	public int shootDelay;
	public int missiles = 10, health = 25;
	
	public void tick(Level level, InputHandler input){
		boolean left = input.keyboard.keys[KeyEvent.VK_LEFT],
				right = input.keyboard.keys[KeyEvent.VK_RIGHT],
				up = input.keyboard.keys[KeyEvent.VK_UP];
		if(left && speed > 0.5)rotation-=3;
		if(right && speed > 0.5)rotation+=3;
		
		rotation = rotation%360;
		while(rotation < 0)rotation += 360;
		
		
		
		if((left || right) && speed > 0.75)speed -= 0.02;
		
		double movementAngle = Math.toRadians(rotation);
		double xMove = (Math.sin(movementAngle) * speed);
		double yMove = (Math.cos(movementAngle) * speed);
		
		if(shootDelay-- <= 0 && input.keyboard.keys[KeyEvent.VK_SPACE]){
			level.entities.add(new Bullet(x+4, y+4, rotation, this));
			shootDelay = 10;

			//level.entities.add(new ExplosionAir(x, y));
		}
			
		
		speed -= yMove/100;
		
		x += xMove;
		y -= yMove;
		
		if(y > 116){
			y = 116;
			if(((rotation > 120 || rotation < 60) && (rotation > 300 || rotation < 240))){
				removed = true;
				level.entities.add(new Particle(x+6, y+6, 2));
				level.entities.add(new Particle(x+6, y+6, 2));
				level.entities.add(new Particle(x+6, y+6, 2));
				level.entities.add(new Particle(x+6, y+6, 2));
				level.entities.add(new Particle(x+6, y+6, 2));
				level.entities.add(new Particle(x+6, y+6, 2));
				level.entities.add(new Particle(x+6, y+6, 2));
				level.entities.add(new Particle(x+6, y+6, 2));
				level.entities.add(new Particle(x+6, y+6, 2));
				level.entities.add(new Particle(x+6, y+6, 2));
				level.entities.add(new Particle(x+6, y+6, 2));
				level.entities.add(new Particle(x+6, y+6, 2));
				level.entities.add(new Particle(x+6, y+6, 2));
				level.entities.add(new Particle(x+6, y+6, 2));
				level.entities.add(new Particle(x+6, y+6, 2));
				level.entities.add(new ExplosionGround(x-1, y-4));
			}
			if(rotation >= 60 && rotation <= 120){
				rotation = 90;
			}else{
				rotation = 270;
			}
		}else if(y == 116){
			if(!up){
				if(speed > 0){
					speed -= 0.01;
				}else{
					speed = 0;
				}
			}
			if(up){
				if(speed < 1.0)speed += 0.01;
			}
		}else{
			if(speed < 1.0)speed += 0.01;
		}
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
