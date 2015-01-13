package dataIO;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class NHIRD_Data {

	/**
	 * Storing encode information
	 */
	public ArrayList<String> encodeKey;
	protected HashMap<String, Integer> encodeBeginInd;
	protected HashMap<String, Integer> encodeEndInd;

	/**
	 * Storing raw data
	 */
	public HashMap<String, String> rawDataMap;

	// Constructor
	public NHIRD_Data(File file) {
		this.rawDataMap = DataIO.readFile(file);

		encode();
	} // end of constructor

	/**
	 * Abstract method to define encode version of data <br>
	 * (By detecting dataLength of one data row)
	 * 
	 * @throws Exception
	 */
	abstract protected void encode();

	/**
	 * Abstract method to make index of data by its type
	 */
	abstract protected void indexing();

	/**
	 * Get item from a single data
	 * 
	 * @param key
	 *            - data key
	 * @param itemKey
	 *            - key of item that wish to get
	 * @return String: retrieved data
	 */
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
	} // end of function getItem()

} // end of class NHIRD_Data
