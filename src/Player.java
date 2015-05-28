import java.awt.Color;
import java.awt.Graphics;

public class Player {
	public static double x, y, diameter, radius;

	public Player(int x, int y, int diameter) {
		this.x = x;
		this.y = y;
		setDiameter((double) diameter);
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
		g.setColor(Color.GREEN);
		g.fillOval((int)(x-radius), (int)(y - radius), (int)diameter, (int)diameter);
	}
}
