import java.awt.Color;
import java.awt.Graphics;

public class Grid {
	private static final int GRID_SIZE = 20;

	public static void render(Graphics g, Component c) {
		g.setColor(Color.GRAY);
		for (int x = 0; x < Component.LEVEL_WIDTH / GRID_SIZE + 1; x++) {
			for (int y = 0; y < Component.LEVEL_HEIGHT / GRID_SIZE + 1; y++) {
				g.drawRect(x * GRID_SIZE, y * GRID_SIZE, GRID_SIZE, GRID_SIZE);
			}
		}
	}
}
