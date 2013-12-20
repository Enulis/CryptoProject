package cryptoproject.keccak.visual;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import cryptoproject.keccak.algorithm.KeccakAlgorithm;
import cryptoproject.keccak.algorithm.KeccakParams;

public class AlgorithmWindow extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private KeccakAlgorithm algorithm;
    private JTextField algorithmInput;
    private JTextField rField;
    private JTextField cField;
    private JTextField nField;
    private JTextArea algorithmOutput;
    private JButton activationButton;
    private JCheckBox showStatesBox;
    private JCheckBox showStepsBox;
    private StateWindow states;
    private final String[] keccakB = { "Keccak224", "Keccak256", "Keccak384",
	    "Keccak512" };
    private JComboBox<String> keccakChooserBox = new JComboBox<>(keccakB);

    public AlgorithmWindow() {

	setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	setBounds(300, 100, 600, 500);
	setTitle("Keccak demo");
	algorithm = new KeccakAlgorithm(KeccakParams.KECCAK_1600);
	initGUI();
	addListeners();
	keccakChooserBox.setSelectedIndex(1);

    }

    private void addListeners() {

	activationButton.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {

		int bitrate;
		int capacity;
		int outputSize;

		try {

		    bitrate = Integer.parseInt(rField.getText());
		    capacity = Integer.parseInt(cField.getText());
		    outputSize = Integer.parseInt(nField.getText());

		    algorithm.setParams(bitrate, capacity, outputSize);
		    algorithm.setDocumentStates(true);
		    algorithmOutput.setText(algorithm
			    .computeHash(algorithmInput.getText()));
		    if (states != null) {
			states.dispose();
		    }

		    if (showStatesBox.isSelected()) {

			states = new StateWindow(algorithm
				.getDocumentedAlgorithmStates(),
				AlgorithmWindow.this);
			states.setVisible(true);

		    } else if (showStepsBox.isSelected()) {

			states = new StateWindow(algorithm
				.getDocumentedAlgorithmSteps(),
				AlgorithmWindow.this);
			states.setVisible(true);

		    }

		} catch (NumberFormatException e1) {

		    JOptionPane.showMessageDialog(AlgorithmWindow.this,
			    "Type the numbers right!", "O come on...",
			    JOptionPane.ERROR_MESSAGE);

		} catch (IllegalArgumentException e2) {

		    JOptionPane.showMessageDialog(AlgorithmWindow.this,
			    e2.getMessage(), "Parameter error",
			    JOptionPane.ERROR_MESSAGE);
		}

	    }
	});
	;

	this.addWindowListener(new WindowListener() {

	    @Override
	    public void windowOpened(WindowEvent e) {

	    }

	    @Override
	    public void windowIconified(WindowEvent e) {

	    }

	    @Override
	    public void windowDeiconified(WindowEvent e) {

	    }

	    @Override
	    public void windowDeactivated(WindowEvent e) {

	    }

	    @Override
	    public void windowClosing(WindowEvent e) {

		if (states != null) {
		    states.dispose();
		}

	    }

	    @Override
	    public void windowClosed(WindowEvent e) {

	    }

	    @Override
	    public void windowActivated(WindowEvent e) {

	    }
	});

	showStatesBox.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent arg0) {

		if (showStepsBox.isSelected()) {
		    showStepsBox.setSelected(false);
		}

	    }
	});

	showStepsBox.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent arg0) {

		if (showStatesBox.isSelected()) {
		    showStatesBox.setSelected(false);
		}
	    }
	});

	keccakChooserBox.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {

		String temp = (String) keccakChooserBox.getSelectedItem();
		int r = 0;
		int c = 0;
		switch (temp) {

		case "Keccak224":
		    r = 1152;
		    c = 224 * 2;
		    break;
		case "Keccak256":
		    r = 1088;
		    c = 256 * 2;
		    break;
		case "Keccak384":
		    r = 832;
		    c = 384 * 2;
		    break;
		case "Keccak512":
		    r = 576;
		    c = 512 * 2;
		}

		rField.setText(Integer.toString(r));
		cField.setText(Integer.toString(c));
		nField.setText(Integer.toString(c / 2));
	    }
	});

    }

    private class ParamPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Component paramField;
	protected JLabel paramLabel;

	public ParamPanel(String paramName, Component c) {

	    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	    this.setMinimumSize(new Dimension(60, 25));
	    paramField = c;
	    paramField.setPreferredSize(new Dimension(60, 25));
	    paramLabel = new JLabel(paramName);
	    paramLabel.setMinimumSize(new Dimension(60, 25));
	    paramLabel.setAlignmentX(CENTER_ALIGNMENT);
	    paramLabel.setAlignmentY(CENTER_ALIGNMENT);

	    this.add(paramLabel);
	    this.add(paramField);

	}

    }

    private void initGUI() {

	this.setMinimumSize(new Dimension(450, 500));
	BorderLayout layout = new BorderLayout();
	layout.setVgap(15);
	this.getContentPane().setLayout(layout);
	algorithmInput = new JTextField();
	JPanel firstPanel = new JPanel();
	firstPanel.setLayout(new BoxLayout(firstPanel, BoxLayout.Y_AXIS));

	JPanel secondPanel = new JPanel();
	secondPanel.setLayout(new BoxLayout(secondPanel, BoxLayout.Y_AXIS));

	algorithmOutput = new JTextArea();
	algorithmOutput.setEditable(false);
	algorithmOutput.setLineWrap(true);

	JLabel label1 = new JLabel("Input text");
	label1.setAlignmentX(CENTER_ALIGNMENT);

	JLabel label2 = new JLabel("The computed Keccak hash value in hex");
	label2.setAlignmentX(CENTER_ALIGNMENT);

	JLabel label3 = new JLabel("Algorithm parameters");
	label3.setAlignmentX(CENTER_ALIGNMENT);

	JScrollPane pane = new JScrollPane(algorithmOutput);

	JPanel activationPanel = new JPanel();
	FlowLayout activationLayout = new FlowLayout();
	activationLayout.setAlignment(FlowLayout.LEFT);
	activationLayout.setAlignOnBaseline(true);
	activationLayout.setHgap(15);
	activationPanel.setLayout(new FlowLayout());

	ParamPanel rPanel = new ParamPanel("Bitrate(r)", new JTextField());
	ParamPanel cPanel = new ParamPanel("Capacity(c)", new JTextField());
	ParamPanel nPanel = new ParamPanel("Output size(n)", new JTextField());
	ParamPanel statePanel = new ParamPanel("Show states", new JCheckBox());
	ParamPanel stepPanel = new ParamPanel("Show steps", new JCheckBox());
	rField = (JTextField) rPanel.paramField;
	cField = (JTextField) cPanel.paramField;
	nField = (JTextField) nPanel.paramField;
	;
	showStatesBox = (JCheckBox) statePanel.paramField;
	showStepsBox = (JCheckBox) stepPanel.paramField;

	activationPanel.add(new ParamPanel("Built-in implementations",
		keccakChooserBox));
	activationPanel.add(rPanel);
	activationPanel.add(cPanel);
	activationPanel.add(nPanel);

	JPanel optionsPanel = new JPanel();
	optionsPanel.setLayout(new FlowLayout());
	optionsPanel.add(statePanel);
	optionsPanel.add(stepPanel);

	activationButton = new JButton("Generate hash");

	firstPanel.add(label1);
	firstPanel.add(algorithmInput);
	firstPanel.add(label3);
	firstPanel.add(activationPanel);
	firstPanel.add(optionsPanel);
	firstPanel.add(activationButton);

	secondPanel.add(label2);
	secondPanel.add(pane);

	this.getContentPane().add(firstPanel, BorderLayout.PAGE_START);
	this.getContentPane().add(secondPanel, BorderLayout.CENTER);

    }

    public static void main(String args[]) {

	SwingUtilities.invokeLater(new Runnable() {
	    @Override
	    public void run() {
		new AlgorithmWindow().setVisible(true);
	    }
	});

    }

}
