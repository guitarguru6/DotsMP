package game.game;

import game.net.GameClient;
import game.net.GameServer;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JOptionPane;

public class Component extends Applet implements Runnable {
	private static final long serialVersionUID = 1L;

	// width and height of the frame
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	// width and height of the level
	public static final int LEVEL_WIDTH = WIDTH * 3;
	public static final int LEVEL_HEIGHT = HEIGHT * 3;

	private static boolean isRunning = false;

	private static Graphics g;
	private static Image level;
	private static Image screen;

	// class that handles user input
	private static Listening listening;

	private Player player;

	// client and server for networking
	private GameClient socketClient;
	private GameServer socketServer;

	public static Window w;

	public static void main(String[] args) {
		Component component = new Component();

		w = new Window(WIDTH, HEIGHT, "DotsMP Pre-alpha v0.2");
		w.add(component);

		component.init();
	}

	public void init() {
		// instantiate the listener
		listening = new Listening();
		addKeyListener(listening);
		addMouseListener(listening);
		addMouseMotionListener(listening);
		addMouseWheelListener(listening);

		// create server if user confirms
		if (JOptionPane.showConfirmDialog(this, "Do you want to run the server?") == 0) {
			socketServer = new GameServer(this);
			socketServer.start();
		}

		// instantiate socketClient
		socketClient = new GameClient(this, "localhost");
		socketClient.start();

		// create the player
		player = new Player(400, 300, 20, JOptionPane.showInputDialog(w, "Please enter a name", "Player"));

		// change the cursor to a set of cross hairs
		setCursor(new Cursor(1));

		// start the game thread
		isRunning = true;
		new Thread(this).start();
	}

	public void run() {
		// while running: tick, render, then attempt to sleep the thread for 10
		// milliseconds
		socketClient.sendData("ping".getBytes());
		while (isRunning) {
			tick();
			render(g);
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void tick() {
		listening.tick();
		player.tick();
	}

	public void renderLevel(Graphics g) {
		// creates the image that represents the level
		level = createImage(LEVEL_WIDTH, LEVEL_HEIGHT);
		g = level.getGraphics();

		// draw black Background
		g.setColor(Color.BLACK);
		g.fillRect((int) Listening.xOff + 400, (int) Listening.yOff + 300, WIDTH, HEIGHT);

		// draw gray grid in background
		Grid.render(g, this);

		// draw border
		g.setColor(Color.RED);
		g.drawRect(1, 1, LEVEL_WIDTH - 2, LEVEL_HEIGHT - 2);

		// draw the player
		player.render(g);
	}

	public void render(Graphics g) {
		// figure out what the level looks like
		renderLevel(g);

		// create the image that will be drawn on the screen
		screen = createImage(WIDTH, HEIGHT);
		g = screen.getGraphics();

		// draw the level in the proper position on the image
		g.drawImage(level, (int) -Listening.xOff - 400, (int) -Listening.yOff - 300, null);

		// show player coordinates in the top left hand corner
		g.setColor(Color.GREEN);
		g.drawString((int) (Player.x) + ", " + (int) (Player.y), 30, 30);

		// actually draws the image to the screen
		g = getGraphics();
		g.drawImage(screen, 0, 0, null);
		g.dispose();
	}

}
