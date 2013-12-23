package endecryption;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.UIManager;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Main extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1967876672889652243L;
	private JPanel contentPane;
	private JButton btnKriptiranjePoruke;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
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
	public Main() {
		
		initComponents();
		createEvents();
		
	}
	
	private void initComponents() {
		setTitle("Kriptografski algoritmi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnSimetricni = new JMenu("Simetricni");
		menuBar.add(mnSimetricni);
		
		JMenuItem mntmAes = new JMenuItem("AES");
		mnSimetricni.add(mntmAes);
		
		JMenuItem mntmDes = new JMenuItem("DES");
		mnSimetricni.add(mntmDes);
		
		JMenu mnAsimetricni = new JMenu("Asimetricni");
		menuBar.add(mnAsimetricni);
		
		JMenuItem mntmRsa = new JMenuItem("RSA");
		mnAsimetricni.add(mntmRsa);
		
		JMenu mnHasj = new JMenu("Hash");
		menuBar.add(mnHasj);
		
		JMenuItem mntmMd = new JMenuItem("MD5");
		mnHasj.add(mntmMd);
		
		JMenuItem mntmSha = new JMenuItem("SHA-1");
		mnHasj.add(mntmSha);
		
		JMenu mnInfo = new JMenu("Info");
		menuBar.add(mnInfo);
		
		JMenuItem mntmLinkNaWeb = new JMenuItem("link na web stranicu");
		mnInfo.add(mntmLinkNaWeb);
		
		JMenu mnIzlaz = new JMenu("Izlaz");
		menuBar.add(mnIzlaz);
		
		JMenuItem mntmKrajSimulacije = new JMenuItem("Kraj simulacije");
		mntmKrajSimulacije.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		mnIzlaz.add(mntmKrajSimulacije);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JToolBar toolBar1 = new JToolBar();
		
		JToolBar toolBar2 = new JToolBar();
		
		JLabel lblOdaberiteAlgoritamU = new JLabel("* Odaberite algoritam u izborniku");
		
		JLabel lblOdaberiteSto = new JLabel("* Odaberite sto zelite kriptirati");
		
		JButton btnKriptiranjeDatoteke = new JButton("Kriptiranje datoteke");
		toolBar2.add(btnKriptiranjeDatoteke);
		
		btnKriptiranjePoruke = new JButton("Kriptiranje poruke");
		
		toolBar1.add(btnKriptiranjePoruke);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(12)
					.addComponent(lblOdaberiteAlgoritamU))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(12)
					.addComponent(lblOdaberiteSto))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(30)
					.addComponent(toolBar1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(toolBar2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(12)
					.addComponent(lblOdaberiteAlgoritamU)
					.addGap(29)
					.addComponent(lblOdaberiteSto)
					.addGap(34)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(toolBar1, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
						.addComponent(toolBar2, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	private void createEvents() {
		btnKriptiranjePoruke.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
	}
}
