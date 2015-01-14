package dataIO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import core.Global;

public class NHIRD_OO_Data extends NHIRD_Data {

	public HashMap<String, HashSet<String>> drugIndex;

	public NHIRD_OO_Data(File file) {
		super(file);

		indexing();
	}
	
	public NHIRD_OO_Data(File[] files) {
		super(files);

		indexing();
	}

	@Override
	protected void encode() {

		String encodeFilePath = Global.encodePath + "OO/";

		int dataLength = this.rawDataMap.get(rawDataMap.keySet().toArray()[0]).length();

		switch (dataLength) {
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
			return;
		}

		File encodeFile = new File(encodeFilePath);
		this.encodeKey = new ArrayList<String>();
		this.encodeBeginInd = new HashMap<String, Integer>();
		this.encodeEndInd = new HashMap<String, Integer>();

		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(encodeFile)));
			while (br.ready()) {
				String[] splitedLine = br.readLine().split(",");

				String key = splitedLine[0];
				int beginInd = Integer.valueOf(splitedLine[1]);
				int endInd = Integer.valueOf(splitedLine[2]);

				this.encodeKey.add(key);
				this.encodeBeginInd.put(key, beginInd);
				this.encodeEndInd.put(key, endInd);
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Read OO encode file success! Encode using: " + encodeFile.getAbsolutePath());

	} // end of function encode()

	@Override
	protected void indexing() {

		String itemKey = new String("DRUG_NO");
		String drug = new String();

		drugIndex = new HashMap<String, HashSet<String>>();

		for (String dataKey : this.rawDataMap.keySet()) {

			drug = this.getItem(dataKey, itemKey);

			if (!drugIndex.containsKey(drug))
				drugIndex.put(drug, new HashSet<String>());

			drugIndex.get(drug).add(dataKey);
		}
	} // end of function indexing()

} // end of class NHIRD_OO_Data
