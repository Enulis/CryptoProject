package endecryption;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class AppC {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				//JFrame frame = new JFrame("Kriptografski algoritmi");
				JFrame frame = new MainFrameC("Kriptografski algoritmi");
				frame.setSize(400, 420);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}
}
