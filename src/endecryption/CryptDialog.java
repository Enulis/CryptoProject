package endecryption;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JToolBar;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

public class CryptDialog extends JDialog {

	private static final long serialVersionUID = 743414514464338326L;
	private final JPanel contentPanel = new JPanel();
	private JButton btnKriptiranjeDatoteke;
	private JButton btnKriptiranjePoruke;
	private JButton btnCbc;
	private JButton btnEcb;
	private JTextField textFieldUnosPoruke;
	private JButton btnOk;
	private JButton btnKriptiraj;
	private JLabel lblRezultatKriptiranja;
	private JTextArea textAreaRezultat;
	private JLabel lblUnosPoruke;
	public static String mode;

	public static void main(String[] args) {
		try {
			CryptDialog dialog = new CryptDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CryptDialog() {
		
		setBounds(100, 100, 400, 650);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel lblOdabirNacinaKriptiranja = new JLabel("Odabir nacina kriptiranja");
		
		JToolBar toolBar = new JToolBar();
		JToolBar toolBar_1 = new JToolBar();
		JToolBar toolBar_2 = new JToolBar();
		JToolBar toolBar_3 = new JToolBar();
		
		mode = "ECB";		//ako korisnik ne odabere onda je po defaultu ECB
		
		lblUnosPoruke = new JLabel("Unos poruke:");
		lblUnosPoruke.hide();
		
		textFieldUnosPoruke = new JTextField();
		textFieldUnosPoruke.setColumns(10);
		textFieldUnosPoruke.hide();
		
		lblRezultatKriptiranja = new JLabel("Rezultat kriptiranja:");
		lblRezultatKriptiranja.hide();
		textAreaRezultat = new JTextArea();
		textAreaRezultat.setEditable(false);
		textAreaRezultat.hide();
		JToolBar toolBar_4 = new JToolBar();
		JToolBar toolBar_5 = new JToolBar();
		
		JPanel panelNapomena = new JPanel();
		panelNapomena.setToolTipText("");
		panelNapomena.setBorder(new TitledBorder(null, "Napomena", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelNapomena.hide();
		
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
								.addComponent(toolBar_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblOdabirNacinaKriptiranja))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(toolBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(toolBar_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addComponent(toolBar_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(6)
							.addComponent(lblUnosPoruke)
							.addGap(12)
							.addComponent(textFieldUnosPoruke, GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(panelNapomena, GroupLayout.PREFERRED_SIZE, 276, GroupLayout.PREFERRED_SIZE)
							.addGap(30)
							.addComponent(toolBar_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(toolBar_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblRezultatKriptiranja)
						.addComponent(textAreaRezultat, GroupLayout.PREFERRED_SIZE, 356, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(toolBar_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(toolBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblOdabirNacinaKriptiranja))
					.addGap(26)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(toolBar_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(toolBar_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(31)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUnosPoruke)
						.addComponent(textFieldUnosPoruke, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(28)
					.addComponent(toolBar_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblRezultatKriptiranja)
					.addGap(18)
					.addComponent(textAreaRezultat, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(toolBar_4, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(panelNapomena, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addContainerGap())
		);
		
		JTextArea txtrUkolikoNeOdabere = new JTextArea();
		txtrUkolikoNeOdabere.setEditable(false);
		txtrUkolikoNeOdabere.setText("Ukoliko ne odabere način rada kriptiranje će se obaviti ECB načinom");
		GroupLayout gl_panelNapomena = new GroupLayout(panelNapomena);
		gl_panelNapomena.setHorizontalGroup(
			gl_panelNapomena.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelNapomena.createSequentialGroup()
					.addContainerGap()
					.addComponent(txtrUkolikoNeOdabere, GroupLayout.PREFERRED_SIZE, 253, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panelNapomena.setVerticalGroup(
			gl_panelNapomena.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelNapomena.createSequentialGroup()
					.addContainerGap()
					.addComponent(txtrUkolikoNeOdabere, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panelNapomena.setLayout(gl_panelNapomena);
		
		btnKriptiraj = new JButton("Kriptiraj");
		toolBar_5.add(btnKriptiraj);
		btnKriptiraj.hide();
		
		btnOk = new JButton("OK");
		toolBar_4.add(btnOk);
		btnOk.hide();
		
		btnKriptiranjeDatoteke = new JButton("Kriptiranje datoteke");
		toolBar_3.add(btnKriptiranjeDatoteke);
		
		btnKriptiranjePoruke = new JButton("Kriptiranje poruke");
		toolBar_2.add(btnKriptiranjePoruke);
		
		btnCbc = new JButton("CBC");
		toolBar_1.add(btnCbc);
		//ako je pozvan od RSA onda btnCbc.hide(); i btnEcb.hide();
		//ostalo sve isto
		
		btnEcb = new JButton("ECB");
		toolBar.add(btnEcb);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
		}
		
		createEvents();
	}
	
	private void createEvents() {
		btnKriptiranjePoruke.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				lblUnosPoruke.setVisible(true);
				lblUnosPoruke.setText("Unos poruke:");
				lblUnosPoruke.show();
				
				textFieldUnosPoruke.show();
				btnKriptiraj.show();
				lblRezultatKriptiranja.show();
				textAreaRezultat.show();	
				
			}
		});
		
		btnKriptiranjeDatoteke.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblUnosPoruke.setVisible(true);
				lblUnosPoruke.setText("Path za datoteku");
				textFieldUnosPoruke.show();
				btnKriptiraj.show();
				lblRezultatKriptiranja.show();
				textAreaRezultat.show();
			}
		});
		
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		
		btnKriptiraj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String poruka = textFieldUnosPoruke.getText();
				//ako jos nema poruke upisane javi gresku
				if (MainFrameC.potvrdaAlgoritma == "AES") {
					//
				}
				else if (MainFrameC.potvrdaAlgoritma == "DES") {
					//
				}
				//pozovi kriptiranje ovisno o tome tko je pozvao
			}
		});
		
		btnEcb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mode = "ECB";
			}
		});
		
		btnCbc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mode = "CBC";
			}
		});
	}
}
