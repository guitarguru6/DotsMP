import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;


public class Component extends Applet implements Runnable {
	private static final long serialVersionUID = 1L;
	
	private static final int WIDTH = 800;
	private static final int HEIGHT = 600;

	private static boolean isRunning = false;

	private static Graphics g;
	private static Image screen;
	
	private static Listening listening;
	
	public static Window w;
	public static void main(String[] args) {
		Component component = new Component();

		w = new Window(WIDTH, HEIGHT, "DotsMP Pre-alpha v0.0.1");
		w.add(component);

		component.init();

	}
	
	public void init() {
		// Instantiate Listener
		listening =  new Listening();
		addKeyListener(listening);
		addMouseListener(listening);
		addMouseMotionListener(listening);
		
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
		
	}
	
	public void render(Graphics g) {
		screen = createImage(WIDTH, HEIGHT);
		g = screen.getGraphics();
		
		// Draw black Background
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		// Draw gray grid in background *temporary*
		g.setColor(Color.GRAY);
		for(int x = 0; x < WIDTH / 10 + 1; x++) {
			for(int y = 0; y < HEIGHT / 10 + 1; y++) {
				g.drawRect(x*10, y*10, 10, 10);
			}
		}
		
		// Draw white crosshair at mouse location
		g.setColor(Color.WHITE);
		g.drawLine(listening.x-5, listening.y, listening.x+5, listening.y);
		g.drawLine(listening.x, listening.y-5, listening.x, listening.y+5);
		
		// Actually draws to the screen
		g = getGraphics();
		g.drawImage(screen, 0, 0, null);
		g.dispose();
	}
	
}
