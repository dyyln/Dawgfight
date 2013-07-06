package com.zzstudios.nfc;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.lang.reflect.Method;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.zzstudios.nfc.assets.Asset;
import com.zzstudios.nfc.assets.Render;
import com.zzstudios.nfc.assets.Screen;
import com.zzstudios.nfc.input.InputHandler;
import com.zzstudios.nfc.level.Level;

public class Game extends Canvas implements Runnable{
	private static final long serialVersionUID = 1L;
	public static int WIDTH = 160, HEIGHT = 144;
	public static int SCREENWIDTH = 320, SCREENHEIGHT = 288;
	public static final String NAME = "Dawgfight";

	public Render render;
	public Screen screen;
	public InputHandler input;
	
	public Game(){
		//setCursor(Toolkit.getDefaultToolkit().createCustomCursor(new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB), new Point(0, 0), "invisible"));
		setSize(new Dimension(SCREENWIDTH, SCREENHEIGHT));
		render = new Render(WIDTH, HEIGHT);
		screen = new Screen(WIDTH, HEIGHT);
		input = new InputHandler(this);
	}
	
	public static int ticks, renders;
	
	public void run() {
		long lastTime = System.nanoTime();
		double nsPerTick = 1000000000.0 / 60.0;
		
		double time = 0;
		
		long lastSecond = System.currentTimeMillis();
		
		this.requestFocus();
		
		while(running){
			long now = System.nanoTime();
			time += (now - lastTime) / nsPerTick;
			lastTime = now;
			boolean render = false;
			while(time >= 1){
				tick();
				time -= 1;
				render = true;
			}
			if(render)render();
			if(System.currentTimeMillis() - lastSecond > 1000){
				lastSecond += 1000;
				System.out.println("FPS - " +renders +", TICKS - " + ticks);
				ticks = renders = 0;
			}
		}
	}

	public static int xo, yo = 1;
	
	private void render() {
		renders++;

		while(yo > 0){
			SCREENHEIGHT+=9;
			SCREENWIDTH+=10;
			xo = getWidth()/2-SCREENWIDTH/2;
			yo = getHeight()/2-SCREENHEIGHT/2-20;
		}
		
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		level.render(screen, input);
		
		render.draw(screen, screen.xKnock, screen.yKnock, 0, 0, WIDTH, HEIGHT);
		
		g.drawImage(render.getImage(), xo, yo+20, SCREENWIDTH, SCREENHEIGHT, null);

		g.dispose();
		bs.show();
	}
	
	public Level level = new Level();
	
	public boolean pressed = false;
	
	Random random = new Random();
	
	public int[] tiles = {random.nextInt(5), random.nextInt(5), random.nextInt(5), random.nextInt(5), random.nextInt(5), random.nextInt(5), random.nextInt(5), random.nextInt(5), random.nextInt(5), random.nextInt(5), random.nextInt(5), random.nextInt(5), random.nextInt(5)};

	private void tick() {
		ticks++;
		if(!input.focus.hasFocus)return;
		level.tick(input);
		if(input.keyboard.keys[KeyEvent.VK_ESCAPE]){
			System.exit(0);
		}
	}
	
	public static Image icon;
	
	public static JFrame frame;
	
	public static void main(String[] args){
		Game game = new Game();
		frame = new JFrame(NAME);
		try{
			icon = ImageIO.read(Game.class.getResourceAsStream("/icon.png"));
			frame.setIconImage(icon);
		}catch(Exception e){
			
		}
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(game, BorderLayout.CENTER);
		frame.setContentPane(panel);
		frame.pack();
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);

		//frame.dispose();
		//frame.setUndecorated(true);

		//frame.setBounds(0,0,frame.getToolkit().getScreenSize().width/2,frame.getToolkit().getScreenSize().height/2);
		
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

    	if (gd.isFullScreenSupported() && false) {
    		try {
    			gd.setFullScreenWindow(frame);
    		}catch(Exception e){
    			frame.setVisible(true);
    		}
    	}else{
			frame.setVisible(true);
			frame.setSize(SCREENWIDTH*2, SCREENHEIGHT*2);
    	}
		
		game.start();
	}
	
	private Thread gameThread;
	
	public boolean running = false;

	public void start() {
		running = true;
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	public void stop() {
		running = false;
	}
}
