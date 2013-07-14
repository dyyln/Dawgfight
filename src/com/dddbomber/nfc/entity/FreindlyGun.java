package com.dddbomber.nfc.entity;

import com.dddbomber.nfc.assets.Asset;
import com.dddbomber.nfc.assets.Screen;
import com.dddbomber.nfc.input.InputHandler;
import com.dddbomber.nfc.level.Level;

public class FreindlyGun extends Entity{

	public FreindlyGun(){
		xSize = 16;
		ySize = 16;
		x = 80-random.nextInt(480);
		y = 112;
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
			level.score += 50;
		}
	}

	public int shootDelay;
	public int health = 15;
	
	public Entity target;

	public void tick(Level level, InputHandler input){
		target = level.getClosestEnemy(this, 250);
		double targetAngle = 0.0;
		if(target != null){
			targetAngle = Math.toDegrees(Math.atan2(target.y-y, target.x-x))+270;
			while(targetAngle < 0)targetAngle += 360;
			targetAngle = targetAngle%360;
		}
			

			if(rotation > targetAngle)rotation-=2;
			if(rotation < targetAngle)rotation+=2;

			while(rotation < 0)rotation += 360;
			rotation = rotation%360;
			shootDelay--;
			if(shootDelay <= 10 && shootDelay % 3 == 0  && rotation > targetAngle-10 && rotation < targetAngle+10){
				level.entities.add(new Bullet(x+4, y+4, rotation+180, this, 4.1));
			}
			if(shootDelay <= 0){
				shootDelay = 90;
			}

		if(y > 116){
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
	}

	public void render(Screen screen, Level level, int xScroll, int yScroll){
		screen.draw(Asset.aagun, (int) x - xScroll, (int) y - yScroll, 0, 0, 16, 16);
		screen.drawRotated(Asset.aagun, (int) x - xScroll-4, (int) y - yScroll-4, 16, 24, 24, 24, (int) (180-rotation));
	}
}