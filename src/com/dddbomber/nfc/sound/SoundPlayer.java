package com.dddbomber.nfc.sound;

import kuusisto.tinysound.Music;
import kuusisto.tinysound.Sound;
import kuusisto.tinysound.TinySound;

public class SoundPlayer {
	
	public static Music music;
	public static Sound shoot, hit, death, missile, ring;
	
	public static void initSounds() {
		TinySound.init();
		shoot = TinySound.loadSound("shoot.wav");
		hit = TinySound.loadSound("hit.wav");
		death = TinySound.loadSound("death.wav");
		missile = TinySound.loadSound("missile.wav");
		ring = TinySound.loadSound("ring.wav");
		
		music = TinySound.loadMusic("music.wav");
	}
}
