package endecryption;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

public class MainFrameC extends JFrame {

	private static final long serialVersionUID = 3516501543574405014L;
	private JTextField textFieldOdabrano;
	private JMenuItem mntmAes;
	private JMenuItem mntmKrajSimulacije;
	private JButton btnPotvrdi;
	private JMenuItem mntmMd5;
	private JMenuItem mntmSha1;
	public static String hashIme;
	private JMenuItem mntmDes;
	private JMenuItem mntmRsa;
	public static String potvrdaAlgoritma;
	private JTextField textFieldOpis1;
	private JTextField txtIzborPotvrdite;
	private JButton btnZatvori;
	private JTextField txtZaKraj;

	
	public MainFrameC(String title){
		super(title);
		
		JPanel detailsPanel = new JPanel();
		
		JPanel panelOpis = new JPanel();
		panelOpis.setBorder(new TitledBorder(null, "Upute", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JToolBar toolBar_1 = new JToolBar();

		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(21)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(detailsPanel, GroupLayout.PREFERRED_SIZE, 389, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
							.addComponent(toolBar_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(panelOpis, GroupLayout.PREFERRED_SIZE, 356, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(45)
					.addComponent(detailsPanel, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelOpis, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
					.addComponent(toolBar_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(29))
		);
		
		btnZatvori = new JButton("Zatvori");
		toolBar_1.add(btnZatvori);
		
		textFieldOpis1 = new JTextField();
		textFieldOpis1.setText("1. U padajućem izborniku odaberite algoritam");
		textFieldOpis1.setEditable(false);
		textFieldOpis1.setColumns(10);
		
		txtIzborPotvrdite = new JTextField();
		txtIzborPotvrdite.setEditable(false);
		txtIzborPotvrdite.setText("2. Izbor potvrdite klikom na tipku Potvrdi");
		txtIzborPotvrdite.setColumns(10);
		
		txtZaKraj = new JTextField();
		txtZaKraj.setEditable(false);
		txtZaKraj.setText("3. Za kraj kliknite Izlaz -> Kraj simulacije");
		txtZaKraj.setColumns(10);
		GroupLayout gl_panelOpis = new GroupLayout(panelOpis);
		gl_panelOpis.setHorizontalGroup(
			gl_panelOpis.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelOpis.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelOpis.createParallelGroup(Alignment.LEADING)
						.addComponent(textFieldOpis1, GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE)
						.addComponent(txtIzborPotvrdite, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE)
						.addComponent(txtZaKraj, GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panelOpis.setVerticalGroup(
			gl_panelOpis.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_panelOpis.createSequentialGroup()
					.addContainerGap()
					.addComponent(textFieldOpis1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(txtIzborPotvrdite, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(txtZaKraj, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panelOpis.setLayout(gl_panelOpis);
		
		JLabel lblOdabrano = new JLabel("Odabrano: ");
		
		textFieldOdabrano = new JTextField();
		textFieldOdabrano.setEditable(false);
		textFieldOdabrano.setColumns(10);
		
		JToolBar toolBar = new JToolBar();
		GroupLayout gl_detailsPanel = new GroupLayout(detailsPanel);
		gl_detailsPanel.setHorizontalGroup(
			gl_detailsPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_detailsPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblOdabrano)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(textFieldOdabrano, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(toolBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(24, Short.MAX_VALUE))
		);
		gl_detailsPanel.setVerticalGroup(
			gl_detailsPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_detailsPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_detailsPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(toolBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_detailsPanel.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblOdabrano)
							.addComponent(textFieldOdabrano, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(63, Short.MAX_VALUE))
		);
		
		btnPotvrdi = new JButton("Potvrdi");
		
		toolBar.add(btnPotvrdi);
		detailsPanel.setLayout(gl_detailsPanel);
		getContentPane().setLayout(groupLayout);
		//
		//Create swing components and add them to content pane
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnSimetricni = new JMenu("Simetricni");
		menuBar.add(mnSimetricni);
		
		mntmAes = new JMenuItem("AES");
//		mntmAes.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//			}
//		});
		mnSimetricni.add(mntmAes);
		
		mntmDes = new JMenuItem("DES");
		
		mnSimetricni.add(mntmDes);
		
		JMenu mnAsimetricni = new JMenu("Asimetricni");
		menuBar.add(mnAsimetricni);
		
		mntmRsa = new JMenuItem("RSA");
		
		mnAsimetricni.add(mntmRsa);
		
		JMenu mnHash = new JMenu("Hash");
		menuBar.add(mnHash);
		
		mntmMd5 = new JMenuItem("MD5");
		mnHash.add(mntmMd5);
		
		mntmSha1 = new JMenuItem("SHA-1");
		mnHash.add(mntmSha1);
		
		JMenu mnIzlaz = new JMenu("Izlaz");
		menuBar.add(mnIzlaz);
		
		mntmKrajSimulacije = new JMenuItem("Kraj simulacije");
		mnIzlaz.add(mntmKrajSimulacije);
		
		JMenu mnInfo = new JMenu("Info");
		menuBar.add(mnInfo);
		
		JMenuItem mntmLinkNaWeb = new JMenuItem("link na web stranicu");
		mnInfo.add(mntmLinkNaWeb);
		
		createEvents();
	}
	
	private void createEvents() {
		mntmAes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textFieldOdabrano.setText(mntmAes.getText());
				potvrdaAlgoritma = "AES";
			}
		});
		
		mntmDes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textFieldOdabrano.setText(mntmDes.getText());
				potvrdaAlgoritma = "DES";
			}
		});
		
		mntmRsa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldOdabrano.setText(mntmRsa.getText());
				potvrdaAlgoritma = "RSA";
			}
		});
		
		mntmKrajSimulacije.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		
		btnPotvrdi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//String algorithm = textFieldOdabrano.getText();
				textFieldOdabrano.setText("Potvrdeno");
				if (potvrdaAlgoritma == "AES") {
					CryptDialog cryptDialogAES = new CryptDialog();
					cryptDialogAES.setTitle("Kriptiranje AES algoritmom");
					cryptDialogAES.show();
					//frameAES.setSize(500, 400);
				}
				else if (potvrdaAlgoritma == "DES") {
					CryptDialog cryptDialogDES = new CryptDialog();
					cryptDialogDES.setTitle("Kriptiranje DES algoritmom");
					cryptDialogDES.show();
				}	
				else if (potvrdaAlgoritma == "RSA") {
					RSADialog2 rsaDialog = new RSADialog2();
					rsaDialog.setTitle("Kriptiranje RSA algoritmom");
					rsaDialog.show();
				}
				else if (potvrdaAlgoritma == "MD5") {
					HashDialog hashDialogMd5 = new HashDialog();
					hashDialogMd5.setTitle("Sazimanje poruke MD5");
					hashDialogMd5.show();
					hashIme = "MD5";
				}
				else if (potvrdaAlgoritma == "SHA-1") {
					HashDialog hashDialogSha1 = new HashDialog();
					hashDialogSha1.setTitle("Sazimanje poruke SHA-1");
					hashDialogSha1.show();
					hashIme = "SHA-1";
				}
				else {
					//javi poruku o gresci
				}
			}
		});
		
		mntmMd5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textFieldOdabrano.setText(mntmMd5.getText());
				potvrdaAlgoritma = "MD5";
			}
		});
		
		mntmSha1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldOdabrano.setText(mntmSha1.getText());
				potvrdaAlgoritma = "SHA-1";
			}
		});
		
		btnZatvori.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}
}
