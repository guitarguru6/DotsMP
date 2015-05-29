import javax.swing.*;

public class Window extends JFrame {
	private static final long serialVersionUID = 1L;

	public Window(int width, int height, String title) {
		setTitle(title);
		setSize(width + 6, height + 28);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
}
