import java.applet.Applet;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;

public class Component extends Applet implements Runnable {
	private static final long serialVersionUID = 1L;

	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;

	private static boolean isRunning = false;

	private static Graphics g;
	private static Image level;
	private static Image screen;

	private static Listening listening;

	public static Window w;

	public static void main(String[] args) {
		Component component = new Component();

		w = new Window(WIDTH, HEIGHT, "DotsMP Pre-alpha v0.0.3");
		w.add(component);

		component.init();
	}

	public void init() {
		// Instantiate Listener
		listening = new Listening();
		addKeyListener(listening);
		addMouseListener(listening);
		addMouseMotionListener(listening);

		level = createVolatileImage(WIDTH, HEIGHT);

		// Change the cursor to a set of crosshairs
		setCursor(new Cursor(1));

		// Start Thread
		isRunning = true;
		new Thread(this).start();
	}

	public void run() {
		while (isRunning) {
			tick();
			render(g);

			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	public void tick() {
		listening.tick();
	}

	public void renderLevel(Graphics g) {
		level = createImage(WIDTH * 3, HEIGHT * 3);
		g = level.getGraphics();

		// Draw black Background
		g.setColor(Color.BLACK);
		g.fillRect((int) Listening.xOff + 400, (int) Listening.yOff + 300, WIDTH, HEIGHT);

		g.setColor(Color.RED);
		g.drawRect(1, 1, level.getWidth(null)-2, level.getHeight(null)-2);
		g.fillRect(100, 100, 200, 200);
	}

	public void render(Graphics g) {
		renderLevel(g);

		screen = createImage(WIDTH, HEIGHT);
		g = screen.getGraphics();

		// Draw black Background
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		g.drawImage(level, (int) -Listening.xOff - 400, (int) -Listening.yOff - 300, null);

		// Draw gray grid in background
		Grid.render(g, this);

		// Draw dead zone
		listening.render(g);

		// Show position
		g.setColor(Color.GREEN);
		g.drawString(Listening.xOff + ", " + Listening.yOff, 30, 30);

		// Actually draws to the screen
		g = getGraphics();
		g.drawImage(screen, 0, 0, null);
		g.dispose();
	}

}
