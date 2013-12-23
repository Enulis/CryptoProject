package endecryption;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

public class RSADialog2 extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldUnosPoruke1;
	private JTextField textFieldUnosPoruke3;
	private JButton btnKriptiraj1;
	private JButton btnKriptiraj2;
	private JButton btnDekriptiraj3;
	private JButton btnDekriptiraj4;
	
	private JTextField textField;
	private JTextArea textAreaRezultat1;
	private JTextField textFieldRezultat2;
	private JTextArea textAreaRezultat3;
	
	private JButton btnOk1;
	private JButton btnOk2;
	private JButton btnOk3;
	private JButton btnOk4;
	private JButton btnOdaberi21;
	private JButton btnOdaberi22;
	private JButton btnOdaberi3;
	private JButton btnOdaberi41;
	private JButton btnOdaberi42;
	public static String datotekaTekst;
	public static String datotekaKljuc;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RSADialog2 dialog = new RSADialog2();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RSADialog2() {
		setBounds(100, 100, 450, 362);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPanel.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel pnlOne = new JPanel();
		tabbedPane.addTab("Kriptiranje poruke", null, pnlOne, null);
		
		JLabel lblUnosPoruke1 = new JLabel("Unos poruke");
		textFieldUnosPoruke1 = new JTextField();
		textFieldUnosPoruke1.setColumns(10);
		
		btnKriptiraj1 = new JButton("Kriptiraj");
		
		
		JPanel panel1 = new JPanel();
		panel1.setBorder(new TitledBorder(null, "Rezultat", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		btnOk1 = new JButton("OK");
		
		GroupLayout gl_pnlOne = new GroupLayout(pnlOne);
		gl_pnlOne.setHorizontalGroup(
			gl_pnlOne.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlOne.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnlOne.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlOne.createSequentialGroup()
							.addComponent(lblUnosPoruke1)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(textFieldUnosPoruke1, GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE))
						.addComponent(btnKriptiraj1)
						.addGroup(gl_pnlOne.createParallelGroup(Alignment.TRAILING)
							.addComponent(btnOk1)
							.addComponent(panel1, GroupLayout.PREFERRED_SIZE, 403, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_pnlOne.setVerticalGroup(
			gl_pnlOne.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlOne.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnlOne.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUnosPoruke1)
						.addComponent(textFieldUnosPoruke1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(27)
					.addComponent(btnKriptiraj1)
					.addGap(18)
					.addComponent(panel1, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnOk1)
					.addContainerGap(24, Short.MAX_VALUE))
		);
		
		textAreaRezultat1 = new JTextArea();
		textAreaRezultat1.setWrapStyleWord(true);
		
		GroupLayout gl_panel1 = new GroupLayout(panel1);
		gl_panel1.setHorizontalGroup(
			gl_panel1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel1.createSequentialGroup()
					.addContainerGap()
					.addComponent(textAreaRezultat1, GroupLayout.PREFERRED_SIZE, 375, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panel1.setVerticalGroup(
			gl_panel1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel1.createSequentialGroup()
					.addComponent(textAreaRezultat1, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(57, Short.MAX_VALUE))
		);
		panel1.setLayout(gl_panel1);
		pnlOne.setLayout(gl_pnlOne);
		
		JPanel pnlTwo = new JPanel();
		tabbedPane.addTab("Kriptiranje datoteke", null, pnlTwo, null);
		
		JLabel lblOdaberiDatotekuZa = new JLabel("Odaberi datoteku za kriptiranje");
		btnOdaberi21 = new JButton("Odaberi");
		JLabel lblOdaberiDatotekuS = new JLabel("Odaberi datoteku s ključem");
		btnOdaberi22 = new JButton("Odaberi");
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Rezultat", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		textFieldRezultat2 = new JTextField();
		textFieldRezultat2.setEditable(false);
		textFieldRezultat2.setColumns(10);
		
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(textFieldRezultat2, GroupLayout.DEFAULT_SIZE, 369, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(textFieldRezultat2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(115, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		btnOk2 = new JButton("OK");
		btnKriptiraj2 = new JButton("Kriptiraj");
		
		GroupLayout gl_pnlTwo = new GroupLayout(pnlTwo);
		gl_pnlTwo.setHorizontalGroup(
			gl_pnlTwo.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlTwo.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnlTwo.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlTwo.createSequentialGroup()
							.addComponent(lblOdaberiDatotekuZa)
							.addPreferredGap(ComponentPlacement.RELATED, 93, Short.MAX_VALUE)
							.addComponent(btnOdaberi21))
						.addGroup(gl_pnlTwo.createSequentialGroup()
							.addComponent(lblOdaberiDatotekuS)
							.addPreferredGap(ComponentPlacement.RELATED, 123, Short.MAX_VALUE)
							.addComponent(btnOdaberi22))
						.addComponent(btnKriptiraj2)
						.addGroup(gl_pnlTwo.createParallelGroup(Alignment.TRAILING)
							.addComponent(btnOk2)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 403, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_pnlTwo.setVerticalGroup(
			gl_pnlTwo.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlTwo.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnlTwo.createParallelGroup(Alignment.LEADING)
						.addComponent(lblOdaberiDatotekuZa)
						.addComponent(btnOdaberi21))
					.addGap(18)
					.addGroup(gl_pnlTwo.createParallelGroup(Alignment.LEADING)
						.addComponent(lblOdaberiDatotekuS)
						.addComponent(btnOdaberi22))
					.addGap(18)
					.addComponent(btnKriptiraj2)
					.addGap(18)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnOk2)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		pnlTwo.setLayout(gl_pnlTwo);
		
		JPanel pnlThree = new JPanel();
		tabbedPane.addTab("Dekriptiranje poruke", null, pnlThree, null);
		
		JLabel lblUnosPoruke3 = new JLabel("Unos poruke");
		textFieldUnosPoruke3 = new JTextField();
		textFieldUnosPoruke3.setColumns(10);
		
		JLabel lblOdabirKljuca3 = new JLabel("Odaberi datoteku s ključem");
		btnOdaberi3 = new JButton("Odaberi");		
		btnDekriptiraj3 = new JButton("Dekriptiraj");
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Rezultat", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		textAreaRezultat3 = new JTextArea();
		textAreaRezultat3.setWrapStyleWord(true);
		
		
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(textAreaRezultat3, GroupLayout.PREFERRED_SIZE, 375, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addComponent(textAreaRezultat3, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(26, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		
		btnOk3 = new JButton("OK");
		
		GroupLayout gl_pnlThree = new GroupLayout(pnlThree);
		gl_pnlThree.setHorizontalGroup(
			gl_pnlThree.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlThree.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnlThree.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_1, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 403, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_pnlThree.createSequentialGroup()
							.addComponent(lblUnosPoruke3, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(textFieldUnosPoruke3, GroupLayout.PREFERRED_SIZE, 301, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_pnlThree.createSequentialGroup()
							.addComponent(lblOdabirKljuca3)
							.addPreferredGap(ComponentPlacement.RELATED, 180, Short.MAX_VALUE)
							.addComponent(btnOdaberi3))
						.addComponent(btnDekriptiraj3)
						.addComponent(btnOk3, Alignment.TRAILING))
					.addContainerGap())
		);
		gl_pnlThree.setVerticalGroup(
			gl_pnlThree.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlThree.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnlThree.createParallelGroup(Alignment.LEADING)
						.addComponent(textFieldUnosPoruke3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblUnosPoruke3))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_pnlThree.createParallelGroup(Alignment.LEADING)
						.addComponent(lblOdabirKljuca3)
						.addComponent(btnOdaberi3))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnDekriptiraj3)
					.addGap(18)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnOk3)
					.addContainerGap(14, Short.MAX_VALUE))
		);
		pnlThree.setLayout(gl_pnlThree);
		
		JPanel pnlFour = new JPanel();
		tabbedPane.addTab("Dekriptiranje datoteke", null, pnlFour, null);
		
		JLabel lblOdaberiDatotekuZa4 = new JLabel("Odaberi datoteku za dekriptiranje");
		btnOdaberi41 = new JButton("Odaberi");
		JLabel lblOdaberiDatotekuSKljucem4 = new JLabel("Odaberi datoteku s ključem");
		btnOdaberi42 = new JButton("Odaberi");
		btnDekriptiraj4 = new JButton("Dekriptiraj");
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Rezultat", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setColumns(10);
		
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGap(0, 403, Short.MAX_VALUE)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addComponent(textField, GroupLayout.DEFAULT_SIZE, 369, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGap(0, 107, Short.MAX_VALUE)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(54, Short.MAX_VALUE))
		);
		panel_2.setLayout(gl_panel_2);
		
		btnOk4 = new JButton("OK");
		
		GroupLayout gl_pnlFour = new GroupLayout(pnlFour);
		gl_pnlFour.setHorizontalGroup(
			gl_pnlFour.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlFour.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnlFour.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_pnlFour.createSequentialGroup()
							.addComponent(lblOdaberiDatotekuZa4)
							.addPreferredGap(ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
							.addComponent(btnOdaberi41, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_pnlFour.createSequentialGroup()
							.addComponent(lblOdaberiDatotekuSKljucem4, GroupLayout.PREFERRED_SIZE, 195, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 123, Short.MAX_VALUE)
							.addComponent(btnOdaberi42, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE))
						.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 403, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnOk4, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnDekriptiraj4, Alignment.LEADING))
					.addContainerGap())
		);
		gl_pnlFour.setVerticalGroup(
			gl_pnlFour.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlFour.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnlFour.createParallelGroup(Alignment.LEADING)
						.addComponent(btnOdaberi41)
						.addComponent(lblOdaberiDatotekuZa4))
					.addGap(18)
					.addGroup(gl_pnlFour.createParallelGroup(Alignment.LEADING)
						.addComponent(btnOdaberi42)
						.addComponent(lblOdaberiDatotekuSKljucem4))
					.addGap(18)
					.addComponent(btnDekriptiraj4)
					.addGap(18)
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnOk4)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		pnlFour.setLayout(gl_pnlFour);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
		}
		
		createEvents();
	}
	
	private void createEvents() {
		
		btnKriptiraj1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String poruka = textFieldUnosPoruke1.getText();
				try {
					textAreaRezultat1.setText(RSA.RSAStringEncrypt(poruka));	//0 za string, 1 za datoteku
				} catch (InvalidKeyException | NoSuchAlgorithmException
						| NoSuchPaddingException | IllegalBlockSizeException
						| BadPaddingException | InvalidKeySpecException
						| IOException e) {
					e.printStackTrace();
				}
			}
		});
		
		btnOk1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		
		btnOk2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		btnOk3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		
		btnOk4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		
		btnOdaberi21.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					handleFileOpenClick21(e);
				}
		});
		
		btnOdaberi22.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					handleFileOpenClick22(e);
				}
		});
		
		btnOdaberi3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handleFileOpenClick22(e);	//isto samo za kljuc
			}
		});
		
		btnOdaberi41.addActionListener(new ActionListener() {	//datoteka s tekstom, provjeri
			public void actionPerformed(ActionEvent e) {
				handleFileOpenClick21(e);
			}
		});
		
		btnOdaberi42.addActionListener(new ActionListener() {	//datoteka s kljucem
			public void actionPerformed(ActionEvent e) {
				handleFileOpenClick22(e);
			}
		});
		
		
		btnDekriptiraj4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//napravi
			}
		});
		
		btnDekriptiraj3.addActionListener(new ActionListener() {	//greska za nepravilan kljuc
			public void actionPerformed(ActionEvent e) {
				//RSA. nesto za dekriptiranje stringa
				String poruka = textFieldUnosPoruke3.getText();
				try {
					try {
						textAreaRezultat3.setText(RSA.decryptString(poruka, datotekaKljuc));
					} catch (IllegalBlockSizeException | BadPaddingException
							| InvalidKeySpecException e1) {
						e1.printStackTrace();
					}	//DEKRIPTIRANJE
				} catch (InvalidKeyException | NoSuchAlgorithmException
						| NoSuchPaddingException | IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		btnKriptiraj2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//RSA.RSAFileEncrypt
				String rezultat;
				try {
					rezultat = RSA.RSAFileEncrypt(datotekaTekst);
					textFieldRezultat2.setText(rezultat);	//field promijeni u area
				} catch (InvalidKeyException | NoSuchAlgorithmException
						| NoSuchPaddingException | IOException e1) {
					e1.printStackTrace();
				}
			}
		});
	}
	
	private void handleFileOpenClick21(ActionEvent arg0) {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
		    "TXT files", "txt");
		chooser.setFileFilter(filter);
		//chooser.setCurrentDirectory()		//
		int returnVal = chooser.showOpenDialog(this);
		if(returnVal == JFileChooser.APPROVE_OPTION) {
		   //System.out.println("You chose to open this file: " +
		        //chooser.getSelectedFile().getPath());
			datotekaTekst = chooser.getSelectedFile().getPath();
		}
	}
	
	private void handleFileOpenClick22(ActionEvent arg0) {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
		    "TXT files", "txt");
		chooser.setFileFilter(filter);
		//chooser.setCurrentDirectory()		//
		int returnVal = chooser.showOpenDialog(this);
		if(returnVal == JFileChooser.APPROVE_OPTION) {
		   //System.out.println("You chose to open this file: " +
		        //chooser.getSelectedFile().getName());
			datotekaKljuc = chooser.getSelectedFile().getPath();
		}
	}
}
