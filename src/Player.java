import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Player {
	public static String name; 
	public static double x, y, diameter, radius;
	public static int minX = 0, minY = 0, maxX = 2400, maxY = 1800;
	private static double speedBase = 50;
	private static double minSize = 10.0;
	private static Color color;
	private static Color[] colors;

	public Player(int x, int y, int diameter, String name) {
		Player.x = x;
		Player.y = y;
		Player.name = name;
		setDiameter((double) diameter);
		setColor();
	}
	
	public static double getSpeed() {
		return speedBase/diameter;
	}

	public static void increaseD() {
		setDiameter(diameter + 1);
	}

	public static void decreaseD() {
		setDiameter(diameter - 1);
	}

	public static void setColor() {
		colors = new Color[] { Color.GREEN, Color.BLUE, Color.CYAN, Color.MAGENTA, Color.ORANGE, Color.PINK, Color.RED, Color.YELLOW };
		color = colors[new Random().nextInt(colors.length)];
	}

	public static void setDiameter(Double d) {
		if(d < minSize) d = minSize;
		diameter = d;
		radius = d / 2.0;
	}

	public void tick() {
		if (x < minX + radius) {
			x = minX + radius;
		}
		if (y < minY + radius) {
			y = minY + radius;
		}
		if (x > maxX - radius) {
			x = maxX - radius;
		}
		if (y > maxY - radius) {
			y = maxY - radius;
		}
	}

	public void render(Graphics g) {
		g.setColor(color);
		g.fillOval((int) (x - radius), (int) (y - radius), (int) diameter, (int) diameter);
		g.setColor(Color.BLACK);
		g.drawString(name, (int)(x-name.length()/2*7), (int)(y-10)-1);
		g.setColor(Color.BLACK);
		g.drawString(name, (int)(x-name.length()/2*7), (int)(y-10)+1);
		g.setColor(Color.BLACK);
		g.drawString(name, (int)(x-name.length()/2*7)-1, (int)(y-10));
		g.setColor(Color.BLACK);
		g.drawString(name, (int)(x-name.length()/2*7)+1, (int)(y-10));
		g.setColor(Color.WHITE);
		g.drawString(name, (int)(x-name.length()/2*7), (int)(y-10));
		
	}
}
