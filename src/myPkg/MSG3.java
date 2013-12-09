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
import javax.swing.JLabel;

public class MSG3 extends JInternalFrame {
	private JTextField txtbyteserverchallenge;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField txtNthashMduserpassword;
	private JTextField textField_3;
	private JTextField txtChallengeresponseDes;
	private JTextField txtDes;
	private JTextField txtDes_1;
	private JTextField txtDes_2;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MSG3 frame = new MSG3();
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
	public MSG3() {
		setFrameIcon(new ImageIcon(MSG3.class.getResource("/gui/resources/gybc_iconMessage.png")));
		setTitle("MSG3");
		setBounds(100, 100, 700, 350);
		
		JPanel panel = new JPanel();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 689, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(21, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		);
		
		txtbyteserverchallenge = new JTextField();
		txtbyteserverchallenge.setFont(new Font("Dialog", Font.BOLD, 14));
		txtbyteserverchallenge.setText("16ByteClientChallenge:");
		txtbyteserverchallenge.setEditable(false);
		txtbyteserverchallenge.setColumns(10);
		
		JTextArea txtrServeraljeKorisniku = new JTextArea();
		txtrServeraljeKorisniku.setFont(new Font("Dialog", Font.BOLD, 12));
		txtrServeraljeKorisniku.setBackground(UIManager.getColor("InternalFrame.borderColor"));
		txtrServeraljeKorisniku.setEditable(false);
		txtrServeraljeKorisniku.setText("Korisnik odgovara poslužitelju porukom MSG3 - u poruci se šalje 16 okteta Challenge hasha(H2), \n24 okteta odgovora na izazov(H2) i korisničko ime(UN).");
		
		textField = new JTextField();
		textField.setBackground(Color.WHITE);
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setText(MSCHAP.CC);
		
		JLabel lblNewLabel = new JLabel("(H1)ChallengeHash = SHA1(ClientChallenge||ServerChallenge||Username):");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		
		textField_1 = new JTextField();
		textField_1.setText((String) null);
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBackground(Color.WHITE);
		textField_1.setText(MSCHAP.SHA1Hash);
		
		txtNthashMduserpassword = new JTextField();
		txtNthashMduserpassword.setText("NTHash = MD4(userPassword):");
		txtNthashMduserpassword.setFont(new Font("Dialog", Font.BOLD, 14));
		txtNthashMduserpassword.setEditable(false);
		txtNthashMduserpassword.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setText((String) null);
		textField_3.setEditable(false);
		textField_3.setColumns(10);
		textField_3.setBackground(Color.WHITE);
		textField_3.setText(MSCHAP.NThash);
		
		txtChallengeresponseDes = new JTextField();
		txtChallengeresponseDes.setText("(H2)ChallengeResponse = DES1 || DES2 || DES3:");
		txtChallengeresponseDes.setFont(new Font("Dialog", Font.BOLD, 14));
		txtChallengeresponseDes.setEditable(false);
		txtChallengeresponseDes.setColumns(10);
		
		txtDes = new JTextField();
		txtDes.setText("DES1(ChallengeHash):");
		txtDes.setFont(new Font("Dialog", Font.BOLD, 14));
		txtDes.setEditable(false);
		txtDes.setColumns(10);
		
		txtDes_1 = new JTextField();
		txtDes_1.setText("DES2(ChallengeHash):");
		txtDes_1.setFont(new Font("Dialog", Font.BOLD, 14));
		txtDes_1.setEditable(false);
		txtDes_1.setColumns(10);
		
		txtDes_2 = new JTextField();
		txtDes_2.setText("DES3(ChallengeHash):");
		txtDes_2.setFont(new Font("Dialog", Font.BOLD, 14));
		txtDes_2.setEditable(false);
		txtDes_2.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setText((String) null);
		textField_5.setEditable(false);
		textField_5.setColumns(10);
		textField_5.setBackground(Color.WHITE);
		textField_5.setText(MSCHAP.D1);
		
		textField_6 = new JTextField();
		textField_6.setText((String) null);
		textField_6.setEditable(false);
		textField_6.setColumns(10);
		textField_6.setBackground(Color.WHITE);
		textField_6.setText(MSCHAP.D2);
		
		textField_7 = new JTextField();
		textField_7.setText((String) null);
		textField_7.setEditable(false);
		textField_7.setColumns(10);
		textField_7.setBackground(Color.WHITE);
		textField_7.setText(MSCHAP.D3);
		
		textField_8 = new JTextField();
		textField_8.setText((String) null);
		textField_8.setEditable(false);
		textField_8.setColumns(10);
		textField_8.setBackground(Color.WHITE);
		textField_8.setText(MSCHAP.ChallengeRsp);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(txtrServeraljeKorisniku, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 642, Short.MAX_VALUE)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(txtbyteserverchallenge, GroupLayout.PREFERRED_SIZE, 203, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textField, GroupLayout.PREFERRED_SIZE, 433, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblNewLabel)
								.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 641, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(txtNthashMduserpassword, GroupLayout.PREFERRED_SIZE, 252, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
									.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, 378, GroupLayout.PREFERRED_SIZE))
								.addComponent(txtChallengeresponseDes, GroupLayout.PREFERRED_SIZE, 390, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(42)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(txtDes_2, GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
								.addComponent(txtDes_1, GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
								.addComponent(txtDes, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(textField_6)
								.addComponent(textField_5, GroupLayout.DEFAULT_SIZE, 415, Short.MAX_VALUE)
								.addComponent(textField_7)))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(textField_8, GroupLayout.DEFAULT_SIZE, 642, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(txtrServeraljeKorisniku, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtbyteserverchallenge, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtNthashMduserpassword, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(12)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtDes, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtDes_1, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtDes_2, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(txtChallengeresponseDes, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField_8, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(29))
		);
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);

	}
}
