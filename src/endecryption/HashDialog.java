package endecryption;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JToolBar;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.security.NoSuchAlgorithmException;

public class HashDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldHash;
	private JButton btnSazmiPoruku;
	private JTextArea textAreaHash;
	

	public static void main(String[] args) {
		try {
			HashDialog dialog = new HashDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public HashDialog() {
		setBounds(100, 100, 450, 230);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel lblUnosHash = new JLabel("Unos poruke:");
		
		textFieldHash = new JTextField();
		textFieldHash.setColumns(10);
		
		JToolBar toolBar = new JToolBar();
		JLabel lblRezultat = new JLabel("Rezultat:");
		textAreaHash = new JTextArea();
		
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(textAreaHash, GroupLayout.PREFERRED_SIZE, 413, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblUnosHash, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
							.addComponent(textFieldHash, GroupLayout.PREFERRED_SIZE, 251, GroupLayout.PREFERRED_SIZE))
						.addComponent(toolBar, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblRezultat))
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(textFieldHash, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblUnosHash))
					.addGap(18)
					.addComponent(toolBar, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblRezultat)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(textAreaHash, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		btnSazmiPoruku = new JButton("Sazmi poruku");
		
		toolBar.add(btnSazmiPoruku);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
		}
		
		createEvents();
	}
	
	private void createEvents() {
		btnSazmiPoruku.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String poruka = textFieldHash.getText();
				textAreaHash.setText(poruka);
				try {
					textAreaHash.setText(Hash.napraviHash(poruka, MainFrameC.hashIme));
				} catch (NoSuchAlgorithmException e1) {
					e1.printStackTrace();
				}
				//napravi i pokazi rezultat
				
			}
		});
	}
}
