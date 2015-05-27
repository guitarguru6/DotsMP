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
		listening =  new Listening();
		addKeyListener(listening);
		addMouseListener(listening);
		addMouseMotionListener(listening);
		
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
	
	public void render(Graphics g) {
		screen = createImage(WIDTH, HEIGHT);
		g = screen.getGraphics();
		
		// Draw black Background
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		// Draw gray grid in background *temporary*
		g.setColor(Color.GRAY);
		for(int x = -1; x < WIDTH / 10 + 1; x++) {
			for(int y = -1; y < HEIGHT / 10 + 1; y++) {
				g.drawRect(x*10-(int)Listening.xOff%10, y*10-(int)Listening.yOff%10, 10, 10);
			}
		}
		
		// draws dead zone
		listening.render(g);
		
		// Actually draws to the screen
		g = getGraphics();
		g.drawImage(screen, 0, 0, null);
		g.dispose();
	}
	
}
