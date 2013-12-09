package myPkg;

import java.awt.EventQueue;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Color;

import java.awt.Font;

import javax.swing.JButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Panel;
import java.awt.FlowLayout;
import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import java.awt.Polygon;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;




public class GUIBegin {

	private JFrame frame;
	private int helper = 0;
	private JPanel panel;
	private JDesktopPane desktopPane;
	private JLabel lblNewLabel;
	private JButton btnStart_1;
	private MSG2 msg2;
	private MSG3 msg3;
	private MSG4 msg4;
	private MSG5 msg5;
	private JButton btnCancel;
	private JTextArea txtrPokretanjemProgramaI;
	private LogIn lw;

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIBegin window = new GUIBegin();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUIBegin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 369);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		
		desktopPane = new JDesktopPane();
		desktopPane.setDragMode(1);
		desktopPane.setBackground(UIManager.getColor("InternalFrame.borderColor"));
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(desktopPane)
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 698, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 326, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(desktopPane, GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE))
		);
		
		txtrPokretanjemProgramaI = new JTextArea();
		txtrPokretanjemProgramaI.setFont(new Font("Dialog", Font.BOLD, 14));
		txtrPokretanjemProgramaI.setText("Pokretanjem programa i pritiskom na tipku login korisnik \"obavještava\" poslužitelj da se \nželi autenticirati.");
		txtrPokretanjemProgramaI.setBackground(UIManager.getColor("InternalFrame.borderColor"));
		txtrPokretanjemProgramaI.setEditable(false);
		txtrPokretanjemProgramaI.setBounds(12, 12, 686, 39);
		
		lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(GUIBegin.class.getResource("/gui/resources/rsz_msg0.jpg")));
		
		btnCancel = new JButton("Cancel");
		btnStart_1 = new JButton("Start");
		btnStart_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnStart_1.getText().equals("End")) frame.dispose();
				else if(helper == 0){
					lw = new LogIn();
					lw.setLocation(850, 0);
					lw.show();
					helper++;
					lblNewLabel.setIcon(new ImageIcon(GUIBegin.class.getResource("/gui/resources/rsz_msg1.jpg")));
					btnStart_1.setText("Next");
					btnCancel.setText("Previous");
					desktopPane.add(txtrPokretanjemProgramaI);
						}
				else if(helper == 1){
						desktopPane.remove(txtrPokretanjemProgramaI);
						lblNewLabel.setIcon(new ImageIcon(GUIBegin.class.getResource("/gui/resources/rsz_msg2.jpg")));
						helper++;
						msg2 = new MSG2();
						desktopPane.add(msg2);
						msg2.setLocation(10, 10);
						msg2.show();
					}
				else if(helper == 2){
						lblNewLabel.setIcon(new ImageIcon(GUIBegin.class.getResource("/gui/resources/rsz_msg3.jpg")));
						desktopPane.remove(msg2);
						msg3 = new MSG3();
						desktopPane.add(msg3);
						msg3.setLocation(10, 10);
						msg3.show();
						helper++;
					}
				else if(helper == 3){
					lblNewLabel.setIcon(new ImageIcon(GUIBegin.class.getResource("/gui/resources/rsz_msg4.jpg")));
					desktopPane.remove(msg3);
					msg4 = new MSG4();
					desktopPane.add(msg4);
					msg4.setLocation(10, 10);
					msg4.show();
					helper++;
				}
				else if(helper == 4){
					lblNewLabel.setIcon(new ImageIcon(GUIBegin.class.getResource("/gui/resources/rsz_msg5.jpg")));
					desktopPane.remove(msg4);
					msg5 = new MSG5();
					desktopPane.add(msg5);
					msg5.setLocation(10, 10);
					msg5.show();
					btnStart_1.setText("End");
					helper++;
				}
			}
		});
		
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnCancel.getText().equals("Cancel")){
				frame.dispose();
				}
				else if((helper - 1) == 0){
					lw.dispose();
					lblNewLabel.setIcon(new ImageIcon(GUIBegin.class.getResource("/gui/resources/rsz_msg0.jpg")));
					btnStart_1.setText("Start");
					btnCancel.setText("Cancel");
					txtrPokretanjemProgramaI.hide();
					desktopPane.remove(txtrPokretanjemProgramaI);
					helper--;
					desktopPane.remove(msg2);
				}
				else if((helper - 1) == 1){
					btnCancel.setText("Previous");
					btnStart_1.setText("Next");
					lblNewLabel.setIcon(new ImageIcon(GUIBegin.class.getResource("/gui/resources/rsz_msg1.jpg")));
					helper--;
					msg2.hide();
					desktopPane.remove(msg2);
				}
				else if((helper - 1) == 2){
					lblNewLabel.setIcon(new ImageIcon(GUIBegin.class.getResource("/gui/resources/rsz_msg2.jpg")));
					msg3.hide();
					desktopPane.remove(msg3);
					desktopPane.add(msg2);
					msg2.setLocation(10, 10);
					msg2.show();
					helper--;
				}
				else if((helper - 1) == 3){
					lblNewLabel.setIcon(new ImageIcon(GUIBegin.class.getResource("/gui/resources/rsz_msg3.jpg")));
					msg4.hide();
					desktopPane.remove(msg4);
					desktopPane.add(msg3);
					msg3.setLocation(10, 10);
					msg3.show();
					helper--;
				}
				else if((helper - 1) == 4){
					btnStart_1.setText("Next");
					lblNewLabel.setIcon(new ImageIcon(GUIBegin.class.getResource("/gui/resources/rsz_msg4.jpg")));
					msg5.hide();
					desktopPane.remove(msg5);
					desktopPane.add(msg4);
					msg4.setLocation(10, 10);
					msg4.show();
					helper--;
				}
			}
		});
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 686, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(12, Short.MAX_VALUE))
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(217, Short.MAX_VALUE)
					.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnStart_1, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
					.addGap(228))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnStart_1)
						.addComponent(btnCancel))
					.addContainerGap(15, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		frame.getContentPane().setLayout(groupLayout);
		frame.setSize(716, 722);
		frame.getContentPane().add(panel);
		frame.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{desktopPane, frame.getContentPane(), panel}));
		panel.setVisible(true);
		frame.setVisible(true);
	}
}
