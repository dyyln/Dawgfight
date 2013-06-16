package com.dddbomber.bgj;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferStrategy;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.dddbomber.bgj.assets.Render;
import com.dddbomber.bgj.assets.Screen;
import com.dddbomber.bgj.input.InputHandler;

public class Game extends Canvas implements Runnable{
	private static final long serialVersionUID = 1L;
	public static int WIDTH = 480, HEIGHT = 360;
	public static int SCREENWIDTH = 960, SCREENHEIGHT = 720;
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

	public static int xo, yo;
	
	private void render() {
		renders++;
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		render.draw(screen, screen.xKnock, screen.yKnock, 0, 0, WIDTH, HEIGHT);
		
		if(!input.focus.hasFocus || true){
			render.fill(0, 0, WIDTH, HEIGHT, 0xffffff, 50);
			String msg = "CLICK TO FOCUS";
			render.draw(msg, 240-msg.length()*6, 100, 0xffffff, 2);
			render.fill(input.mouse.x-2, input.mouse.y-2, 4, 4, 0xffffff);
		}
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		while(yo > 0){
			SCREENHEIGHT+=3;
			SCREENWIDTH+=4;
			xo = getWidth()/2-SCREENWIDTH/2;
			yo = getHeight()/2-SCREENHEIGHT/2;
		}
		xo = getWidth()/2-SCREENWIDTH/2;
		yo = getHeight()/2-SCREENHEIGHT/2;
		g.drawImage(render.getImage(), xo,  yo, SCREENWIDTH, SCREENHEIGHT, null);

		g.dispose();
		bs.show();
	}
	
	public boolean pressed = false;

	private void tick() {
		ticks++;
		if(!input.focus.hasFocus)return;
	}
	
	public static Image icon;
	
	public static void main(String[] args){
		Game game = new Game();
		JFrame frame = new JFrame(NAME);
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

		frame.dispose();
		frame.setUndecorated(true);

		frame.setBounds(0,0,frame.getToolkit().getScreenSize().width,frame.getToolkit().getScreenSize().height);
		frame.setVisible(true);

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
