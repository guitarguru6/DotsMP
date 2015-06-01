package game.game;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class Listening implements MouseListener, MouseMotionListener, MouseWheelListener, KeyListener {

	// the location of the cursor in the window
	public static int x, y;
	// the camera offset
	public static double xOff = 0, yOff = 0;
	// the boundaries the camera shouldn't cross
	public static int minX = -400, minY = -300, maxX = 1200, maxY = 900;

	public static void updateLocation(MouseEvent e) {
		// updates the location of the mouse
		x = e.getX();
		y = e.getY();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		updateLocation(e);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		updateLocation(e);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	public void tick() {
		// sets the camera position relative to the position of the player
		xOff = Player.x - 800;
		yOff = Player.y - 600;

		// checks that the camera isn't out of bounds and corrects if it is
		if (xOff < minX)
			xOff = minX;
		if (yOff < minY)
			yOff = minY;
		if (xOff > maxX)
			xOff = maxX;
		if (yOff > maxY)
			yOff = maxY;
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		// if scrolled up increase player size, if scrolled down decrease it.
		// (for debugging purposes)
		if (e.getWheelRotation() < 0) {
			Player.increaseD();
		} else if (e.getWheelRotation() > 0) {
			Player.decreaseD();
		}
	}
}
