import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;


public class DeadZone extends Rectangle {
	private static final long serialVersionUID = 1L;
	public Color color;
	
	public DeadZone() {
		setBounds(300, 250, 200, 100);
		color = Color.GREEN;
	}
	
	public void red() {
		color = Color.RED;
	}
	
	public void green() {
		color = Color.GREEN;
	}
	
	public void tick() {
		width = (int) Player.diameter;
		height = (int) Player.diameter;
		x = (int) (Player.x - Listening.xOff) - 400 - (width/2);
		y = (int) (Player.y - Listening.yOff) - 300 - (height/2);
	}
	
	public void render(Graphics g) {
		g.setColor(color);
		g.drawRect(x, y, width, height);
	}

}
