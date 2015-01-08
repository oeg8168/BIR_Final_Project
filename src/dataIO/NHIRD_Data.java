package dataIO;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class NHIRD_Data {

	protected int dataLength;

	public ArrayList<String> encodeKey;
	protected HashMap<String, Integer> encodeBeginInd;
	protected HashMap<String, Integer> encodeEndInd;

	public HashMap<String, String> rawDataMap;

	public NHIRD_Data(File file) throws Exception {
		this.rawDataMap = DataIO.readFile(file);
		this.dataLength = rawDataMap.get(rawDataMap.keySet().toArray()[0]).length();
		encode();
	}

	abstract protected void encode() throws Exception;
	abstract protected void indexing();

	public String getItem(String key, String itemKey) {
		if (rawDataMap.containsKey(key)) {
			if (encodeBeginInd.containsKey(itemKey) && encodeEndInd.containsKey(itemKey)) {
				int beginIndex = encodeBeginInd.get(itemKey) - 1;
				int endIndex = encodeEndInd.get(itemKey);
				String item = rawDataMap.get(key).substring(beginIndex, endIndex);
				return item;
			} else {
				System.err.println("The item " + itemKey + " does not exist!");
			}
		} else {
			System.err.println("There is no data with key: " + key);
		}
		return null;
	}


} // end of class NHIRD_Data
