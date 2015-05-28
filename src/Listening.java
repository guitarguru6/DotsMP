import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class Listening implements MouseListener, MouseMotionListener, MouseWheelListener, KeyListener {

	public static int x, y;
	public static double xOff = 0, yOff = 0;
	public static int minX = -400, minY = -300, maxX = 1200, maxY = 900;
	private static DeadZone dz;

	public Listening() {
		super();
		dz = new DeadZone();
	}

	public static void updateLocation(MouseEvent e) {
		x = e.getX();
		y = e.getY();
	}

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

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public void tick() {
		dz.green();
		if (y < dz.y) {
			Player.y -= 5;
			dz.red();
		}
		if (y > dz.y + dz.height) {
			Player.y += 5;
			dz.red();
		}
		if (x < dz.x) {
			Player.x -= 5;
			dz.red();
		}
		if (x > dz.x + dz.width) {
			Player.x += 5;
			dz.red();
		}
		
		xOff = Player.x - 800;
		yOff = Player.y - 600;

		if (xOff < minX) {
			xOff = minX;
		}
		if (yOff < minY) {
			yOff = minY;
		}
		if (xOff > maxX) {
			xOff = maxX;
		}
		if (yOff > maxY) {
			yOff = maxY;
		}
	}

	public void render(Graphics g) {
		dz.render(g);
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		// TODO Auto-generated method stub
		if(e.getWheelRotation() < 0) {
			Player.increaseD();
		} else if (e.getWheelRotation() > 0) {
			Player.decreaseD();
		}
	}
}
