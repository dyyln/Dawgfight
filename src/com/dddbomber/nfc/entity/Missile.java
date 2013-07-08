package com.dddbomber.nfc.entity;

import com.dddbomber.nfc.assets.Asset;
import com.dddbomber.nfc.assets.Screen;
import com.dddbomber.nfc.input.InputHandler;
import com.dddbomber.nfc.level.Level;

public class Missile extends Entity{
	
	public Entity target, owner;
	
	public Missile(double x, double y, Entity target, Entity owner){
		xSize = 2;
		ySize = 2;
		this.x = x;
		this.y = y;
		this.target = target;
		this.owner = owner;
	}
	
	public double speed = 1.5;
	
	public void tick(Level level, InputHandler input){
		if(target.removed){
			if(rotation < 179)rotation += 0.1;
			if(rotation > 181)rotation -= 0.1;
		}else{
	        double seekDegrees = Math.toDegrees(Math.atan2(y - target.y, x - target.x))-90;
	        rotation = Asset.lerpDegrees(rotation, seekDegrees, 0.1);
		}
            
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
		
		for(int i = 0; i < level.entities.size(); i++){
			Entity e = level.entities.get(i);
			if(e != owner && !(e instanceof Bullet) && !(e instanceof Missile) && !(owner instanceof Enemy && e instanceof Enemy) && !removed){
				if(this.intersects(e)){
					e.damage(level, 3);
					removed = true;
				}
			}
		}
	}
	
	public void render(Screen screen, Level level, int xScroll, int yScroll){
		screen.drawRotated(Asset.bullet, (int) x - xScroll, (int) y - yScroll, 0, 0, 4, 4, (int) -rotation);
	}
}
