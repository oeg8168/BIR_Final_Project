package core;

import java.io.File;
import java.io.IOException;
import dataIO.DataIO;

public class Main {

	public static void main(String[] args) throws IOException {

		
		String testInputPath = new String("C:/Users/LISM_OEG/Desktop/20KX/EX_CD20KX.DAT");
		String testInputPath2 = new String("C:/Users/LISM_OEG/Desktop/t.txt");
		
		DataIO.viewFileContent(new File(testInputPath));
		System.out.println();
		DataIO.viewFileContent(new File(testInputPath2));
		
	}

}
