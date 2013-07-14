package com.dddbomber.nfc.sound;

import kuusisto.tinysound.Sound;
import kuusisto.tinysound.TinySound;

public class SoundPlayer {
	
	public static Sound shoot, hit, death, ground, missile;
	
	public static void initSounds() {
		TinySound.init();
		shoot = TinySound.loadSound("shoot.wav");
		hit = TinySound.loadSound("hit.wav");
		death = TinySound.loadSound("death.wav");
		missile = TinySound.loadSound("missile.wav");
		ground = TinySound.loadSound("ground.wav");
	}
}
