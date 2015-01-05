package dataIO;

import java.io.File;
import java.util.HashMap;

public abstract class NHIRD_Data {
	
	protected int dataLength;
	public HashMap<String, Integer> encodeBeginInd;
	public HashMap<String, Integer> encodeEndInd;
	
	public HashMap<String, String> rawDataMap;
		
	public NHIRD_Data(File file) throws Exception {
		this.rawDataMap = DataIO.readFile(file);
		this.dataLength = rawDataMap.get(rawDataMap.keySet().toArray()[0]).length();
		encode();
	}
	
	abstract protected void encode() throws Exception;
	
	public void getItem(String key, String itemKey){
		if (rawDataMap.containsKey(key)) {
			if (encodeBeginInd.containsKey(itemKey) && encodeEndInd.containsKey(itemKey)) {
				int beginIndex = encodeBeginInd.get(itemKey) - 1;
				int endIndex = encodeEndInd.get(itemKey);
				System.out.println(rawDataMap.get(key).substring(beginIndex, endIndex));
			} else {
				System.err.println("The item " + itemKey + " does not exist!");
			}
		} else {
			System.err.println("There is no data with key: " + key);
		}
	}

	

} // end of class NHIRD_Data
