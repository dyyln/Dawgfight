package com.zzstudios.nfc.entity;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import com.zzstudios.nfc.assets.Asset;
import com.zzstudios.nfc.assets.Screen;
import com.zzstudios.nfc.input.InputHandler;
import com.zzstudios.nfc.level.Level;

public class Bullet extends Entity{
	
	public Entity owner;
	
	public Bullet(double x, double y, double rotation, Entity owner){
		xSize = 2;
		ySize = 2;
		this.x = x;
		this.y = y;
		this.rotation = rotation;
		this.owner = owner;
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
		
		for(int i = 0; i < level.entities.size(); i++){
			Entity e = level.entities.get(i);
			if(e != owner && !(e instanceof Bullet) && !(owner instanceof Enemy && e instanceof Enemy) && !removed){
				if(this.intersects(e)){
					e.damage(level, 1);
					removed = true;
				}
			}
		}
	}
	
	private boolean intersects(Entity e) {
		Rectangle r = new Rectangle((int) x, (int) y, xSize, ySize);
		Rectangle er = new Rectangle((int) e.x, (int) e.y, e.xSize, e.ySize);
		return r.intersects(er);
	}

	public void render(Screen screen, Level level, int xScroll){
		screen.drawRotated(Asset.bullet, (int) x - xScroll, (int) y, 0, 0, 4, 4, (int) -rotation);
	}
}
