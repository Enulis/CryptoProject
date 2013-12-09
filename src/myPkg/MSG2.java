package myPkg;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import java.awt.Color;

public class MSG2 extends JInternalFrame {
	private JTextField txtbyteserverchallenge;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MSG2 frame = new MSG2();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MSG2() {
		setFrameIcon(new ImageIcon(MSG2.class.getResource("/gui/resources/gybc_iconMessage.png")));
		setTitle("MSG2");
		setBounds(100, 100, 700, 350);
		
		JPanel panel = new JPanel();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 665, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
		);
		
		txtbyteserverchallenge = new JTextField();
		txtbyteserverchallenge.setFont(new Font("Dialog", Font.BOLD, 14));
		txtbyteserverchallenge.setText("     (R1)16ByteServerChallenge:");
		txtbyteserverchallenge.setEditable(false);
		txtbyteserverchallenge.setColumns(10);
		
		JTextArea txtrServeraljeKorisniku = new JTextArea();
		txtrServeraljeKorisniku.setFont(new Font("Dialog", Font.BOLD, 12));
		txtrServeraljeKorisniku.setBackground(UIManager.getColor("InternalFrame.borderColor"));
		txtrServeraljeKorisniku.setEditable(false);
		txtrServeraljeKorisniku.setText("Poslužitelj šalje korisniku izazov od 16 okteta (R1) koji korisnik koristi u \ndaljnjem procesu autentikacije.");
		
		textField = new JTextField();
		textField.setBackground(Color.WHITE);
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setText(MSCHAP.SC);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(txtrServeraljeKorisniku, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 641, Short.MAX_VALUE)
						.addComponent(textField, GroupLayout.DEFAULT_SIZE, 641, Short.MAX_VALUE)
						.addComponent(txtbyteserverchallenge, GroupLayout.PREFERRED_SIZE, 275, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(txtrServeraljeKorisniku, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
					.addGap(24)
					.addComponent(txtbyteserverchallenge, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(19, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);

	}
}
