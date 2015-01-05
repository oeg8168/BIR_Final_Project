package dataIO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

import core.Global;

public class NHIRD_OO_Data extends NHIRD_Data {

	public NHIRD_OO_Data(File file) throws Exception {
		super(file);
		
	}

	@Override
	protected void encode() throws Exception {

		String encodeFilePath = Global.encodePath + "OO/";

		switch (this.dataLength) {
		case 119:
			encodeFilePath += "OO_86-95.ecf";
			break;

		case 124:
			encodeFilePath += "OO_96-100.ecf";
			break;

		case 3195:
			encodeFilePath += "OO_101.ecf";
			break;

		default:
			System.err.println("Input file error! (Not OO file)");
			throw new Exception();
		}

		File encodeFile = new File(encodeFilePath);
		this.encodeBeginInd = new HashMap<String, Integer>();
		this.encodeEndInd = new HashMap<String, Integer>();

		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(encodeFile)));
			while (br.ready()) {
				String[] splitedLine = br.readLine().split(",");

				String key = splitedLine[0];
				int beginInd = Integer.valueOf(splitedLine[1]);
				int endInd = Integer.valueOf(splitedLine[2]);

				this.encodeBeginInd.put(key, beginInd);
				this.encodeEndInd.put(key, endInd);
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Read OO encode file success! Encode using: " + encodeFile.getAbsolutePath());

	} // end of function encode()

} // end of class NHIRD_OO_Data
