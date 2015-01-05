package dataIO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

import core.Global;

public class NHIRD_CD_Data extends NHIRD_Data {

	public NHIRD_CD_Data(File file) throws Exception {
		super(file);

	}

	@Override
	protected void encode() throws Exception {

		String encodeFilePath = Global.encodePath + "CD/";

		switch (this.dataLength) {
		case 299:
			encodeFilePath += "CD_85-92.ecf";
			break;

		case 300:
			encodeFilePath += "CD_93-100.ecf";
			break;

		case 377:
			encodeFilePath += "CD_101-.ecf";
			break;

		default:
			System.err.println("Input file error! (Not CD file)");
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

		System.out.println("Read CD encode file success! Encode using: " + encodeFile.getAbsolutePath());

	} // end of function encode()

} // end of class NHIRD_OO_Data
