package GUI;

import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;
import javax.swing.JSlider;

public class MainApplication {

	private JFrame frmNhird;
	private JTextField textFieldDiseaseCode;
	private JTextField textFieldDrugCode;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainApplication window = new MainApplication();
					window.frmNhird.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainApplication() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmNhird = new JFrame();
		frmNhird.setTitle("NHIRD");
		frmNhird.setBounds(100, 100, 600, 480);
		frmNhird.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmNhird.getContentPane().setLayout(null);

		// Block 1: select input files

		JButton btnSelectCDFile = new JButton("Select CD file from disk");
		btnSelectCDFile.setBounds(10, 10, 200, 50);
		frmNhird.getContentPane().add(btnSelectCDFile);

		JButton btnSelectOOFile = new JButton("Select OO file from disk");
		btnSelectOOFile.setBounds(10, 66, 200, 50);
		frmNhird.getContentPane().add(btnSelectOOFile);

		JLabel lblCDFilePath = new JLabel("CD file path: ");
		lblCDFilePath.setLabelFor(btnSelectCDFile);
		lblCDFilePath.setBounds(220, 28, 354, 15);
		frmNhird.getContentPane().add(lblCDFilePath);

		JLabel lblOOFilePath = new JLabel("OO file path: ");
		lblOOFilePath.setLabelFor(btnSelectOOFile);
		lblOOFilePath.setBounds(220, 84, 354, 15);
		frmNhird.getContentPane().add(lblOOFilePath);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 126, 564, 2);
		frmNhird.getContentPane().add(separator_1);

		// Block 2: input disease and drug codes

		JLabel lblDiseaseCode = new JLabel("Disease Code (ICD9): ");
		lblDiseaseCode.setLabelFor(textFieldDiseaseCode);
		lblDiseaseCode.setBounds(10, 141, 130, 15);
		frmNhird.getContentPane().add(lblDiseaseCode);

		JLabel lblDrugCode = new JLabel("Drug Code: ");
		lblDrugCode.setLabelFor(textFieldDrugCode);
		lblDrugCode.setBounds(10, 172, 130, 15);
		frmNhird.getContentPane().add(lblDrugCode);

		textFieldDiseaseCode = new JTextField();
		textFieldDiseaseCode.setBounds(150, 138, 300, 21);
		textFieldDiseaseCode.setColumns(10);
		frmNhird.getContentPane().add(textFieldDiseaseCode);

		textFieldDrugCode = new JTextField();
		textFieldDrugCode.setBounds(150, 169, 300, 21);
		textFieldDrugCode.setColumns(10);
		frmNhird.getContentPane().add(textFieldDrugCode);

		JLabel lblDrugCodeChooseFile = new JLabel("or");
		lblDrugCodeChooseFile.setBounds(150, 204, 20, 15);
		frmNhird.getContentPane().add(lblDrugCodeChooseFile);

		JButton btnBrowseDrugCode = new JButton("Browse Drug Code File");
		btnBrowseDrugCode.setBounds(180, 196, 200, 30);
		frmNhird.getContentPane().add(btnBrowseDrugCode);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(10, 236, 564, 2);
		frmNhird.getContentPane().add(separator_2);

		// Block 3 left: defining sorting orders

		JComboBox<String> comboBox_1 = new JComboBox<String>();
		comboBox_1.setModel(new DefaultComboBoxModel<String>(new String[] { "ID", "Disease", "Drug", "Time" }));
		comboBox_1.setBounds(120, 249, 90, 21);
		frmNhird.getContentPane().add(comboBox_1);

		JLabel lblSortingOrder_1 = new JLabel("1st sorting order");
		lblSortingOrder_1.setLabelFor(comboBox_1);
		lblSortingOrder_1.setBounds(10, 252, 100, 15);
		frmNhird.getContentPane().add(lblSortingOrder_1);

		JComboBox<String> comboBox_2 = new JComboBox<String>();
		comboBox_2.setModel(new DefaultComboBoxModel<String>(new String[] { "ID", "Disease", "Drug", "Time" }));
		comboBox_2.setBounds(120, 280, 90, 21);
		frmNhird.getContentPane().add(comboBox_2);

		JLabel lblSortingOrder_2 = new JLabel("2nd sorting order");
		lblSortingOrder_2.setLabelFor(comboBox_2);
		lblSortingOrder_2.setBounds(10, 283, 100, 15);
		frmNhird.getContentPane().add(lblSortingOrder_2);

		JComboBox<String> comboBox_3 = new JComboBox<String>();
		comboBox_3.setModel(new DefaultComboBoxModel<String>(new String[] { "ID", "Disease", "Drug", "Time" }));
		comboBox_3.setBounds(120, 311, 90, 21);
		frmNhird.getContentPane().add(comboBox_3);

		JLabel lblSortingOrder_3 = new JLabel("3rd sorting order");
		lblSortingOrder_3.setLabelFor(comboBox_3);
		lblSortingOrder_3.setBounds(10, 314, 100, 15);
		frmNhird.getContentPane().add(lblSortingOrder_3);

		JComboBox<String> comboBox_4 = new JComboBox<String>();
		comboBox_4.setModel(new DefaultComboBoxModel<String>(new String[] { "ID", "Disease", "Drug", "Time" }));
		comboBox_4.setBounds(120, 342, 90, 21);
		frmNhird.getContentPane().add(comboBox_4);

		JLabel lblSortingOrder_4 = new JLabel("4th sorting order");
		lblSortingOrder_4.setLabelFor(comboBox_4);
		lblSortingOrder_4.setBounds(10, 345, 100, 15);
		frmNhird.getContentPane().add(lblSortingOrder_4);

		JSeparator separator_3v = new JSeparator();
		separator_3v.setOrientation(SwingConstants.VERTICAL);
		separator_3v.setBounds(220, 248, 4, 112);
		frmNhird.getContentPane().add(separator_3v);

		// Block 3 right: select definite diagnosis threshold

		JSlider sliderThreshold = new JSlider();
		sliderThreshold.setMajorTickSpacing(1);
		sliderThreshold.setMinimum(1);
		sliderThreshold.setMaximum(5);
		sliderThreshold.setValue(1);
		sliderThreshold.setSnapToTicks(true);
		sliderThreshold.setPaintLabels(true);
		sliderThreshold.setPaintTicks(true);
		sliderThreshold.setBounds(234, 273, 200, 50);
		frmNhird.getContentPane().add(sliderThreshold);

		JLabel lblThreshold = new JLabel("Definite Diagnosis Threshold");
		lblThreshold.setLabelFor(sliderThreshold);
		lblThreshold.setBounds(234, 248, 200, 15);
		frmNhird.getContentPane().add(lblThreshold);

		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(10, 370, 564, 2);
		frmNhird.getContentPane().add(separator_3);

		// Block 4:

		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(10, 382, 200, 50);
		frmNhird.getContentPane().add(btnSubmit);

		JButton btnAbout = new JButton("About");
		btnAbout.setBounds(484, 402, 90, 30);
		frmNhird.getContentPane().add(btnAbout);

		// ////////////////////

		btnSelectCDFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser();
				chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					System.out.println(chooser.getSelectedFile().getAbsolutePath());
					lblCDFilePath.setText("CD file path: " + chooser.getSelectedFile().getAbsolutePath());
				}
			}
		});

		btnSelectOOFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser();
				chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					System.out.println(chooser.getSelectedFile().getAbsolutePath());
					lblOOFilePath.setText("OO file path: " + chooser.getSelectedFile().getAbsolutePath());
				}
			}
		});

		btnBrowseDrugCode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser();
				chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					System.out.println(chooser.getSelectedFile().getAbsolutePath());
					textFieldDrugCode.setText(chooser.getSelectedFile().getAbsolutePath());
				}
			}
		});
	}

} // end of class MainApplication
