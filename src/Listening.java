import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Listening implements MouseListener, MouseMotionListener, KeyListener {
	
	public static int x, y;
	public static double xOff = 0, yOff = 0;
	private static int minX = -800, minY = -600, maxX = 1600, maxY = 1200;
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
			yOff -= 2;
			dz.red();
		}
		if (y > dz.y + dz.height) {
			yOff += 2;
			dz.red();
		}
		if (x < dz.x) {
			xOff -= 2;
			dz.red();
		}
		if (x > dz.x + dz.width) {
			xOff += 2;
			dz.red();
		}
		
		if(xOff < minX) {
			xOff = minX;
		}
		if(yOff < minY) {
			yOff = minY;
		}
		if(xOff > maxX) {
			xOff = maxX;
		}
		if(yOff > maxY) {
			yOff = maxY;
		}
	}

	public void render(Graphics g) {
		dz.render(g);
	}
}
