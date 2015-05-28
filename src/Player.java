import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Player {
	public static double x, y, diameter, radius;
	public static int minX = 0, minY = 0, maxX = 2400, maxY = 1800;
	private static Color color;
	private static Color[] colors;

	public Player(int x, int y, int diameter) {
		this.x = x;
		this.y = y;
		setDiameter((double) diameter);
		setColor();
	}
	
	public static void increaseD() {
		setDiameter(diameter + 1);
	}
	
	public static void decreaseD() {
		setDiameter(diameter - 1);
	}
	
	public static void setColor() {
		colors = new Color[]{Color.GREEN, Color.BLUE, Color.CYAN, Color.MAGENTA, Color.ORANGE, Color.PINK, Color.RED, Color.YELLOW};
		color = colors[new Random().nextInt(colors.length)];
	}

	public static void setDiameter(Double d) {
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
		g.fillOval((int)(x-radius), (int)(y - radius), (int)diameter, (int)diameter);
	}
}
