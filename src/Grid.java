import java.awt.Color;
import java.awt.Graphics;

public class Grid {
	private static final int GRID_SIZE = 20;

	public static void render(Graphics g, Component c) {
		g.setColor(Color.GRAY);
		for (int x = -1; x < c.WIDTH / GRID_SIZE + 1; x++) {
			for (int y = -1; y < c.HEIGHT / GRID_SIZE + 1; y++) {
				g.drawRect(x * GRID_SIZE - (int) Listening.xOff % GRID_SIZE, y * GRID_SIZE - (int) Listening.yOff % GRID_SIZE, GRID_SIZE, GRID_SIZE);
			}
		}
	}
}
