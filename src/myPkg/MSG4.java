package myPkg;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.ImageIcon;

public class MSG4 extends JInternalFrame {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MSG4 frame = new MSG4();
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
	public MSG4() {
		setFrameIcon(new ImageIcon(MSG4.class.getResource("/gui/resources/gybc_iconMessage.png")));
		setTitle("MSG4");
		setBounds(100, 100, 700, 350);
		
		JPanel panel = new JPanel();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 665, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE)
		);
		
		JTextArea textArea = new JTextArea();
		textArea.setText("Poslužitelj šalje korisniku odgovor na autentikaciju (AR - authentication response) čime se \nzavršava proces autentikacije.");
		textArea.setFont(new Font("Dialog", Font.BOLD, 12));
		textArea.setEditable(false);
		textArea.setBackground(UIManager.getColor("Button.background"));
		
		JLabel lblKasjd = new JLabel("NTHashHash = MD4(MD4(UserPassword)):");
		lblKasjd.setFont(new Font("Dialog", Font.BOLD, 12));
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setText(MSCHAP.NTHashHash);
		
		JLabel lblDigestShanthashhash = new JLabel("Digest = SHA1(NTHashHash || ChallengeRsp || \"Magic server to client signing constant\"):");
		lblDigestShanthashhash.setFont(new Font("Dialog", Font.BOLD, 12));
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setText(MSCHAP.Digest);
		
		JLabel lblAuth = new JLabel("(AR)AuthRsp = SHA1(Digest || ChallengeHash || \"Pad to make it do more than one iteration\"):");
		lblAuth.setFont(new Font("Dialog", Font.BOLD, 12));
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setText(MSCHAP.Authrsp);
		
		JTextArea textArea_1 = new JTextArea();
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(textArea, GroupLayout.DEFAULT_SIZE, 642, Short.MAX_VALUE)
								.addComponent(lblKasjd, GroupLayout.PREFERRED_SIZE, 345, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField, GroupLayout.DEFAULT_SIZE, 642, Short.MAX_VALUE)
								.addComponent(lblDigestShanthashhash)
								.addComponent(textField_1, GroupLayout.DEFAULT_SIZE, 642, Short.MAX_VALUE)))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(212)
							.addComponent(textArea_1, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblAuth, GroupLayout.PREFERRED_SIZE, 642, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(textField_2, GroupLayout.DEFAULT_SIZE, 666, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblKasjd)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblDigestShanthashhash)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblAuth, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
					.addComponent(textArea_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(39))
		);
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);

	}
}
