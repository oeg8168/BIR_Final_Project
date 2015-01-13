package dataIO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import core.Global;

public class NHIRD_CD_Data extends NHIRD_Data {

	public HashMap<String, HashSet<String>> diseaseIndex;

	public NHIRD_CD_Data(File file) {
		super(file);

		indexing();
	}

	@Override
	protected void encode() {

		String encodeFilePath = Global.encodePath + "CD/";

		int dataLength = this.rawDataMap.get(rawDataMap.keySet().toArray()[0]).length();

		switch (dataLength) {
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

		System.out.println("Read CD encode file success! Encode using: " + encodeFile.getAbsolutePath());

	} // end of function encode()

	@Override
	protected void indexing() {

		String[] itemKeys = { "ACODE_ICD9_1", "ACODE_ICD9_2", "ACODE_ICD9_3" };
		String disease = new String();

		diseaseIndex = new HashMap<String, HashSet<String>>();

		for (String dataKey : this.rawDataMap.keySet()) {
			for (String itemKey : itemKeys) {

				disease = this.getItem(dataKey, itemKey);

				if (!diseaseIndex.containsKey(disease))
					diseaseIndex.put(disease, new HashSet<String>());

				diseaseIndex.get(disease).add(dataKey);
			}
		}
	}// end of function indexing()

} // end of class NHIRD_OO_Data
