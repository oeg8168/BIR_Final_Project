package dataIO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

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
	}

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
		
		return map;
	}

} // end of class DataIO
