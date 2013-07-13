package com.dddbomber.nfc.entity;

import com.dddbomber.nfc.assets.Asset;
import com.dddbomber.nfc.assets.Screen;
import com.dddbomber.nfc.input.InputHandler;
import com.dddbomber.nfc.level.Level;

public class Enemy extends Entity{

	public Enemy(){
		xSize = 8;
		ySize = 8;
		x = 80+random.nextInt(480);
		y = 76;
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

	public double speed = 0.0;
	public int shootDelay;
	public int health = 15;
	public boolean loop = false;

	public int runAway = 600;
	private int loopsDone, evasions = 1;

	public void tick(Level level, InputHandler input){
		if(runAway % 3 == 0){
			if(health < 5){
				double rot = Math.toRadians(rotation+160+random.nextInt(41));
				level.entities.add(new Particle(x+6, y+8, 1, Math.sin(rot)*1.0, Math.cos(rot)*-1.0));
			}
			if(health < 3){
				double rot = Math.toRadians(rotation+160+random.nextInt(41));
				level.entities.add(new Particle(x+6, y+8, 2, Math.sin(rot)*1.0, Math.cos(rot)*-1.0));
			}
		}

		runAway--;
		if(runAway < 0)runAway = 600;
		if(runAway % 60 == 0 && y <= 80 && health < 8 && random.nextInt(evasions*2)==0 && random.nextBoolean()){
			loop = true;
		}

		if(!loop){
			double target = Math.toDegrees(Math.atan2(level.player.y-y, level.player.x-x))+90;
			if(runAway < 200)target += 180;
			while(target < 0)target += 360;
			target = target%360;

			if(rotation > target)rotation-=1;
			if(rotation < target)rotation+=1;

			while(rotation < 0)rotation += 360;
			rotation = rotation%360;

			int xo = (int) (x-xScroll);
			if(xo > 192)rotation = target;
			if(xo < -48)rotation = target;

			if(y > 80){
				if(rotation > 180){
					rotation+=3;
				}else{
					rotation-=3;
				}
			}
			if(shootDelay-- <= 0 && rotation > target-10 && rotation < target+10){
				level.entities.add(new Bullet(x+4, y+4, rotation, this, speed+1.0));
				shootDelay = 30;

				//level.entities.add(new ExplosionAir(x, y));
			}
		}else{
			rotation -= rotation%3;
			rotation += 3;
			if(rotation%360 == 0){
				loopsDone++;
				if(loopsDone >= 2){
					loop = false;
					evasions++;
					loopsDone = 0;
				}
			}
		}

		//if((left || right) && speed > 0.75)speed -= 0.02;

		double movementAngle = Math.toRadians(rotation);
		double xMove = (Math.sin(movementAngle) * speed);
		double yMove = (Math.cos(movementAngle) * speed);



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

		speed -= yMove/1000;

		x += xMove;
		y -= yMove;

		if(speed < 1.4)speed += 0.01;
	}

	public int xScroll;

	public void render(Screen screen, Level level, int xScroll, int yScroll){
		this.xScroll = xScroll;
		if(rotation == 90){
			screen.draw(Asset.plane, (int) x - xScroll, (int) y - yScroll, 16, 16, 16, 16);
		}else if(rotation == 270){
			screen.draw(Asset.plane, (int) x - xScroll, (int) y - yScroll, 32, 16, 16, 16);
		}else{
			screen.drawRotated(Asset.plane, (int) x - xScroll, (int) y - yScroll, (rotation > 180 ? 64 : 48), 16, 16, 16, (int) -rotation);
		}
	}
}