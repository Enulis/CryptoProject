package myPkg;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import java.awt.Font;
import javax.swing.ImageIcon;

public class MSG5 extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MSG5 frame = new MSG5();
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
	public MSG5() {
		setFrameIcon(new ImageIcon(MSG5.class.getResource("/gui/resources/exclamation_trans_bg_icon.gif")));
		setTitle("Propusti u protokolu MSCHAP");
		setBounds(100, 100, 700, 350);
		
		JPanel panel = new JPanel();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 686, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE)
					.addGap(24))
		);
		
		JTextArea txtrGlavniNedostatakProtokola = new JTextArea();
		txtrGlavniNedostatakProtokola.setEditable(false);
		txtrGlavniNedostatakProtokola.setFont(new Font("Dialog", Font.BOLD, 14));
		txtrGlavniNedostatakProtokola.setText("Glavni nedostataci protokola MS CHAP su korištenje zastarjelih algoritama"
				+ "\nMD4 i DES. Iz podataka dostupnih preko alata (npr. Wireshark) koji "
				+ "\nhvataju pakete, moguće je na jednostavan način doći do paketa kojima korisnik "
				+ "\nkomunicira s poslužiteljem prilikom autentikacije. "
				+ "\nDovoljno je samo izračunati MD4(userPassword) iz Challenge Responsa kako bi se "
				+ "\nmogli autenticirati kao ciljani korisnik. Uz današnje sklopovlje, unutar 24 sata, "
				+ "\nmoguće je \"pogoditi\" lozinku, bez obzira na njezinu dužinu i jakost.");
		txtrGlavniNedostatakProtokola.setBackground(UIManager.getColor("InternalFrame.inactiveTitleBackground"));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(24, Short.MAX_VALUE)
					.addComponent(txtrGlavniNedostatakProtokola, GroupLayout.PREFERRED_SIZE, 662, GroupLayout.PREFERRED_SIZE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(22)
					.addComponent(txtrGlavniNedostatakProtokola, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(135, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);

	}
}
