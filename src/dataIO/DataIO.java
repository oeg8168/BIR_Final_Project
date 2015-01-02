package dataIO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

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
	
	

}
