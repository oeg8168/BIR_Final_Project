package dataIO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Class for data I/O
 * 
 * @author LISM_OEG
 *
 */
public class DataIO {

	/**
	 * Print file content in console
	 * 
	 * @param file
	 */
	public static void viewFileContent(File file) {

		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			while (br.ready()) {
				System.out.println(br.readLine());

			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // end of function viewFileContent()

	/**
	 * Read file and store into HashMap
	 * 
	 * @param file
	 * @return HashMap: Contains data from input file.
	 */
	public static HashMap<String, String> readFile(File file) {

		HashMap<String, String> map = new HashMap<String, String>();
		String tempString = new String();
		String keyString = new String();

		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			while (br.ready()) {
				tempString = br.readLine();
				keyString = tempString.substring(0, 57);
				map.put(keyString, tempString);
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Read file success! File path: " + file.getAbsolutePath());

		return map;
	}// end of function readFile()

	/**
	 * Read drug files get from http://www.nhi.gov.tw/Query/Query1.aspx
	 * 
	 * @param file
	 *            - input file
	 * @return HashSet: DRUG_NO
	 */
	public static HashSet<String> readDrugFile(File file) {

		HashSet<String> drugSet = new HashSet<String>();

		String temp;
		int indexL;
		int indexR;

		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			while (br.ready()) {
				temp = br.readLine();
				if (temp.contains("lblQ1ID")) {
					indexL = temp.indexOf(">");
					indexR = temp.indexOf("<", indexL);
					drugSet.add(temp.substring(indexL + 1, indexR));
				}
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return drugSet;
	} // end of function readDrugFile()

} // end of class DataIO
