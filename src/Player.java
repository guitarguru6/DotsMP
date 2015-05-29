import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Player {
	public static String name;
	public static double x, y, diameter, radius;
	public static int mass;
	// level boundaries
	public static int minX = 0, minY = 0, maxX = Component.LEVEL_WIDTH, maxY = Component.LEVEL_HEIGHT;
	// the base number used to calculate the speed of the player
	private static double speedBase = 200.0;
	private static int minSize = 20;
	private static Color color;
	private static Color[] colors;

	public Player(int x, int y, int mass, String name) {
		Player.x = x;
		Player.y = y;
		Player.name = name;
		setMass(mass);
		setColor();
	}

	public static void move(double d, double e) {
		// checks that the point is not inside the player
		if (!contains(d, e)) {
			// calculates x and y distances between the point and the center of
			// the player
			double deltaX = d - x;
			double deltaY = e - y;
			// does some trigonometric magic and gets the direction
			double direction = Math.atan2(deltaY, deltaX);
			// calculates the proper speed given the mass of the player
			double speed = speedBase / mass;
			// moves the player in the calculated direction at the calculated
			// speed
			x += (speed * Math.cos(direction));
			y += (speed * Math.sin(direction));
			// checks if the next movement will overshoot and corrects if it
			// does
			if (Math.abs(deltaX) <= speed && Math.abs(deltaY) <= speed) {
				x = d;
				y = e;
			}
		}
	}

	public static void increaseD() {
		// increases mass by 1
		changeMass(1);
	}

	public static void decreaseD() {
		// decreases mass by 1
		changeMass(-1);
	}

	public static void setMass(int d) {
		// checks that the resulting final mass will not be less than the
		// minimum allowed
		if (d < minSize)
			// if it is less, fix it
			d = minSize;
		mass = d;
		setDiameter((double) d);
	}

	public static void changeMass(int d) {
		setMass(mass + d);
	}

	public static void setColor() {
		// creates an array of preset colors
		colors = new Color[] { Color.GREEN, Color.BLUE, Color.CYAN, Color.MAGENTA, Color.ORANGE, Color.RED, Color.YELLOW };
		// chooses a color from the array and changes the player to that color
		color = colors[new Random().nextInt(colors.length)];
	}

	public static void setDiameter(Double d) {
		diameter = d;
		radius = d / 2.0;
	}

	public static boolean contains(double d, double e) {
		// calculates the distance between point (d, e) and the center of the
		// player
		double dist = Math.sqrt(Math.pow(d - x, 2) + Math.pow(e - y, 2));
		// returns true if the point is inside of the player
		return dist < radius;
	}

	public void tick() {
		// move the player
		move(Listening.x + Listening.xOff + 400, Listening.y + Listening.yOff + 300);

		// check if player is out of bounds, if so, bring the player back in
		if (x < minX + radius)
			x = minX + radius;
		if (y < minY + radius)
			y = minY + radius;
		if (x > maxX - radius)
			x = maxX - radius;
		if (y > maxY - radius)
			y = maxY - radius;
	}

	public void drawStringWithOutline(String s, double x, double y, Graphics g) {
		// draw the outline of the string in black
		g.setColor(Color.BLACK);
		g.drawString(s, (int) (x - s.length() / 2.0 * 7), (int) y - 1);
		g.drawString(s, (int) (x - s.length() / 2.0 * 7), (int) y + 1);
		g.drawString(s, (int) (x - s.length() / 2.0 * 7) - 1, (int) y);
		g.drawString(s, (int) (x - s.length() / 2.0 * 7) + 1, (int) y);
		// draw the string itself in white
		g.setColor(Color.WHITE);
		g.drawString(s, (int) (x - s.length() / 2.0 * 7), (int) (y));
	}

	public void render(Graphics g) {
		// draw the circle that represents the player
		g.setColor(color);
		g.fillOval((int) (x - radius), (int) (y - radius), (int) diameter, (int) diameter);

		// draw player name
		drawStringWithOutline(name, x, y - 10, g);
		// draw player mass
		drawStringWithOutline(mass + "", x, y + 4, g);
	}
}
