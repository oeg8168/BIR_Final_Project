package GUI;

import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;
import javax.swing.JSlider;
import javax.swing.filechooser.FileNameExtensionFilter;

import core.CoreAlg;
import dataIO.DataIO;
import dataIO.NHIRD_CD_Data;
import dataIO.NHIRD_OO_Data;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashSet;
import java.awt.Color;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableWorkbook;

public class MainApplication {

	private JFrame frmNhird;
	private String CDFilePath;
	private String OOFilePath;
	private String outputPath;
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
		frmNhird.setResizable(false);
		frmNhird.setTitle("NHIRD");
		frmNhird.setBounds(100, 100, 600, 530);
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

		JLabel lblDrugCodeFileRemind = new JLabel("Please use excel file downloaded from");
		lblDrugCodeFileRemind.setLabelFor(btnBrowseDrugCode);
		lblDrugCodeFileRemind.setBounds(180, 236, 394, 15);
		frmNhird.getContentPane().add(lblDrugCodeFileRemind);

		JLabel lblLink = new JLabel("<html><u>http://www.nhi.gov.tw/Query/Query1.aspx</u></html>");
		lblLink.setLabelFor(btnBrowseDrugCode);
		lblLink.setBounds(180, 261, 394, 15);
		frmNhird.getContentPane().add(lblLink);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(10, 286, 564, 2);
		frmNhird.getContentPane().add(separator_2);

		// Block 3 left: defining sorting orders

		JComboBox<String> comboBox_1 = new JComboBox<String>();
		comboBox_1.setModel(new DefaultComboBoxModel<String>(new String[] { "ID", "Disease", "Drug", "Time" }));
		comboBox_1.setBounds(120, 299, 90, 21);
		frmNhird.getContentPane().add(comboBox_1);

		JLabel lblSortingOrder_1 = new JLabel("1st sorting order");
		lblSortingOrder_1.setLabelFor(comboBox_1);
		lblSortingOrder_1.setBounds(10, 302, 100, 15);
		frmNhird.getContentPane().add(lblSortingOrder_1);

		JComboBox<String> comboBox_2 = new JComboBox<String>();
		comboBox_2.setModel(new DefaultComboBoxModel<String>(new String[] { "ID", "Disease", "Drug", "Time" }));
		comboBox_2.setBounds(120, 330, 90, 21);
		frmNhird.getContentPane().add(comboBox_2);

		JLabel lblSortingOrder_2 = new JLabel("2nd sorting order");
		lblSortingOrder_2.setLabelFor(comboBox_2);
		lblSortingOrder_2.setBounds(10, 333, 100, 15);
		frmNhird.getContentPane().add(lblSortingOrder_2);

		JComboBox<String> comboBox_3 = new JComboBox<String>();
		comboBox_3.setModel(new DefaultComboBoxModel<String>(new String[] { "ID", "Disease", "Drug", "Time" }));
		comboBox_3.setBounds(120, 361, 90, 21);
		frmNhird.getContentPane().add(comboBox_3);

		JLabel lblSortingOrder_3 = new JLabel("3rd sorting order");
		lblSortingOrder_3.setLabelFor(comboBox_3);
		lblSortingOrder_3.setBounds(10, 364, 100, 15);
		frmNhird.getContentPane().add(lblSortingOrder_3);

		JComboBox<String> comboBox_4 = new JComboBox<String>();
		comboBox_4.setModel(new DefaultComboBoxModel<String>(new String[] { "ID", "Disease", "Drug", "Time" }));
		comboBox_4.setBounds(120, 392, 90, 21);
		frmNhird.getContentPane().add(comboBox_4);

		JLabel lblSortingOrder_4 = new JLabel("4th sorting order");
		lblSortingOrder_4.setLabelFor(comboBox_4);
		lblSortingOrder_4.setBounds(10, 395, 100, 15);
		frmNhird.getContentPane().add(lblSortingOrder_4);

		JSeparator separator_3v = new JSeparator();
		separator_3v.setOrientation(SwingConstants.VERTICAL);
		separator_3v.setBounds(220, 298, 4, 112);
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
		sliderThreshold.setBounds(234, 323, 200, 50);
		frmNhird.getContentPane().add(sliderThreshold);

		JLabel lblThreshold = new JLabel("Definite Diagnosis Threshold");
		lblThreshold.setLabelFor(sliderThreshold);
		lblThreshold.setBounds(234, 298, 200, 15);
		frmNhird.getContentPane().add(lblThreshold);

		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(10, 420, 564, 2);
		frmNhird.getContentPane().add(separator_3);

		// Block 4:

		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(10, 432, 200, 50);
		frmNhird.getContentPane().add(btnSubmit);

		JLabel lblSubmitCheck = new JLabel("");
		lblSubmitCheck.setForeground(Color.RED);
		lblSubmitCheck.setBounds(220, 432, 260, 50);
		frmNhird.getContentPane().add(lblSubmitCheck);

		JButton btnAbout = new JButton("About");
		btnAbout.setBounds(484, 452, 90, 30);
		frmNhird.getContentPane().add(btnAbout);

		// ////////////////////

		btnSelectCDFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser();
				chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					System.out.println(chooser.getSelectedFile().getAbsolutePath());
					CDFilePath = chooser.getSelectedFile().getAbsolutePath();
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
					OOFilePath = chooser.getSelectedFile().getAbsolutePath();
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

		lblLink.setForeground(Color.BLUE);
		lblLink.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblLink.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (Desktop.isDesktopSupported()) {
					try {
						URI uri = new URI("http://www.nhi.gov.tw/Query/Query1.aspx");
						Desktop.getDesktop().browse(uri);
					} catch (IOException ex) {
						ex.printStackTrace();
					} catch (URISyntaxException ex) {
						ex.printStackTrace();
					}
				}

			}
		});

		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				boolean passFlag = true;
				String alertString = new String("<html>");

				if (OOFilePath == null || CDFilePath == null) {
					alertString += "Please select CD/OO file!<br>";
					passFlag = false;
				}

				if (textFieldDiseaseCode.getText().isEmpty() && textFieldDrugCode.getText().isEmpty()) {
					alertString += "Please enter query! (Disease or Drug)<br>";
					passFlag = false;
				}

				if (!passFlag) {
					alertString += "</html>";
					lblSubmitCheck.setText(alertString);

				} else {
					lblSubmitCheck.setText("Processing...");

					NHIRD_OO_Data OO = new NHIRD_OO_Data(new File(OOFilePath));
					NHIRD_CD_Data CD = new NHIRD_CD_Data(new File(CDFilePath));

					HashSet<String> diseases = CoreAlg.parseQuery(textFieldDiseaseCode.getText());
					HashSet<String> drugs;

					File drugFile = new File(textFieldDrugCode.getText());
					if (drugFile.isFile()) {
						drugs = DataIO.readDrugFile(drugFile);
					} else {
						drugs = CoreAlg.parseQuery(textFieldDrugCode.getText());
					}

					HashSet<String> queryResult = CoreAlg.query(CD, OO, diseases, drugs);

					String[] sortingOrder = { comboBox_1.getSelectedItem().toString(), comboBox_2.getSelectedItem().toString(), comboBox_3.getSelectedItem().toString(), comboBox_4.getSelectedItem().toString() };

					ArrayList<String> sortedKeys = CoreAlg.sortByOrder(CD, OO, queryResult, sortingOrder);
					
					JFileChooser chooser = new JFileChooser();
					chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
						System.out.println(chooser.getSelectedFile().getAbsolutePath());
						outputPath = chooser.getSelectedFile().getAbsolutePath();
					}
					
					try {
						WritableWorkbook workbook = Workbook.createWorkbook(new File(outputPath + "/output.xls"));
						
						workbook.createSheet("result", 0);
						
						workbook.getSheet(0).addCell(new Label(0,0,"ID"));
						workbook.getSheet(0).addCell(new Label(1,0,"ID_SEX"));
						workbook.getSheet(0).addCell(new Label(2,0,"ID_BIRTHDAY"));
						workbook.getSheet(0).addCell(new Label(3,0,"ACODE_ICD9_1"));
						workbook.getSheet(0).addCell(new Label(4,0,"ACODE_ICD9_2"));
						workbook.getSheet(0).addCell(new Label(5,0,"ACODE_ICD9_3"));
						workbook.getSheet(0).addCell(new Label(6,0,"DRUG_NO"));
						workbook.getSheet(0).addCell(new Label(7,0,"DRUG_DAY"));
						workbook.getSheet(0).addCell(new Label(8,0,"TOTAL_QTY"));
						workbook.getSheet(0).addCell(new Label(9,0,"FUNC_DATE"));
						
						int row = 1;
						for (String key : sortedKeys) {
							
							workbook.getSheet(0).addCell(new Label(0,row,CD.getItem(key, "ID")));
							workbook.getSheet(0).addCell(new Label(1,row,CD.getItem(key, "ID_SEX")));
							workbook.getSheet(0).addCell(new Label(2,row,CD.getItem(key, "ID_BIRTHDAY")));
							workbook.getSheet(0).addCell(new Label(3,row,CD.getItem(key, "ACODE_ICD9_1")));
							workbook.getSheet(0).addCell(new Label(4,row,CD.getItem(key, "ACODE_ICD9_2")));
							workbook.getSheet(0).addCell(new Label(5,row,CD.getItem(key, "ACODE_ICD9_3")));
							workbook.getSheet(0).addCell(new Label(6,row,OO.getItem(key, "DRUG_NO")));
							workbook.getSheet(0).addCell(new Label(7,row,CD.getItem(key, "DRUG_DAY")));
							workbook.getSheet(0).addCell(new Label(8,row,OO.getItem(key, "TOTAL_QTY")));
							workbook.getSheet(0).addCell(new Label(9,row,CD.getItem(key, "FUNC_DATE")));
							
							row++;
						}
						
						workbook.write();
						workbook.close();
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					lblSubmitCheck.setText("");
				}

			}
		});

		btnAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(frmNhird, "<html>aaa<br>ddd</html>", "Title", JOptionPane.PLAIN_MESSAGE);
			}
		});

	}
} // end of class MainApplication
