import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Player {
	public static double x, y, diameter, radius;
	private static Color color;
	private static Color[] colors;

	public Player(int x, int y, int diameter) {
		this.x = x;
		this.y = y;
		setDiameter((double) diameter);
		setColor();
	}
	
	public static void setColor() {
		colors = new Color[]{Color.GREEN, Color.BLUE, Color.CYAN, Color.MAGENTA, Color.ORANGE, Color.PINK, Color.RED, Color.YELLOW};
		color = colors[new Random().nextInt(colors.length)];
	}

	public void setDiameter(Double d) {
		diameter = d;
		radius = d / 2.0;
	}
	
	public void tick() {
		x = Listening.xOff+800;
		y = Listening.yOff+600;
	}
	
	public void render(Graphics g) {
		g.setColor(color);
		g.fillOval((int)(x-radius), (int)(y - radius), (int)diameter, (int)diameter);
	}
}
