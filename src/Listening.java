import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Listening implements MouseListener, MouseMotionListener, KeyListener {
	
	public static int x, y;
	public static double xOff = 0, yOff = 0;
	public static boolean up = false, down = false, left = false, right = false;
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
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_W) {
			up = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_A) {
			left = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_S) {
			down = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_D) {
			right = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_W) {
			up = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_A) {
			left = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_S) {
			down = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_D) {
			right = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public void tick() {
		dz.green();
		if (up || y < dz.y) {
			yOff -= 1;
			dz.red();
		}
		if (down || y > dz.y + dz.height) {
			yOff += 1;
			dz.red();
		}
		if (left || x < dz.x) {
			xOff -= 1;
			dz.red();
		}
		if (right || x > dz.x + dz.width) {
			xOff += 1;
			dz.red();
		}
	}

	public void render(Graphics g) {
		dz.render(g);
	}
}
