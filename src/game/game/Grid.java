package game.game;
import java.awt.Color;
import java.awt.Graphics;

public class Grid {
	// the width and height of a single cell
	private static final int GRID_SIZE = 20;

	public static void render(Graphics g, Component c) {
		g.setColor(Color.GRAY);
		// this draws the grid itself onto the level, it is so long because it
		// is optimized to only draw where it will be visible
		for (int x = (int) (Listening.xOff / GRID_SIZE); x < (Listening.xOff / GRID_SIZE + Component.LEVEL_WIDTH / GRID_SIZE + 1); x++) {
			for (int y = (int) (Listening.yOff / GRID_SIZE); y < (Listening.yOff / GRID_SIZE + Component.LEVEL_HEIGHT / GRID_SIZE + 1); y++) {
				g.drawRect(x * GRID_SIZE, y * GRID_SIZE, GRID_SIZE, GRID_SIZE);
			}
		}
	}
}
