package cryptoproject.keccak.visual;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class StateWindow extends JFrame{
    
    /**
     * 
     */
    private static final long serialVersionUID = -3459281414858259847L;
    private String info;
    private Component parent;
    
    public StateWindow(String info, Component parent) {
	
	this.info = info;
	this.parent = parent;
	initGUI();
	
    }

    private void initGUI() {
	
	this.setTitle("Algorithm states");
	this.setLayout(new BorderLayout());
	JPanel panel = new JPanel();
	panel.setLayout(new BorderLayout());
	JTextArea textArea = new JTextArea();
	textArea.setText(info);
	textArea.setEditable(false);
	JScrollPane pane = new JScrollPane(textArea);
	panel.add(pane, BorderLayout.CENTER);
	this.setBounds(parent.getX() + parent.getWidth(), parent.getY(), 200, 200);
	this.setMinimumSize(new Dimension(200,200));
	this.getContentPane().add(panel, BorderLayout.CENTER);
	
	
    }
    

}
