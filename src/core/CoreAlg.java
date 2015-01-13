package core;

import java.util.HashSet;

import dataIO.NHIRD_CD_Data;
import dataIO.NHIRD_OO_Data;

public class CoreAlg {

	/**
	 * Parse input query
	 * 
	 * @param input
	 *            - String want to parse
	 * @return HashSet: parsed result
	 */
	public static HashSet<String> parseQuery(String input) {

		HashSet<String> resultQuery = new HashSet<String>();

		// Remove all spaces
		input = input.replaceAll(" ", "");

		for (String string : input.split(",")) {
			if (string.contains("-")) {
				String begin = string.split("-")[0];
				String end = string.split("-")[1];

				try {
					for (int i = Integer.valueOf(begin); i <= Integer.valueOf(end); i++) {
						resultQuery.add(String.valueOf(i));
					}
				} catch (Exception e) {
					System.err.println("Query error!");
					System.err.println(e);
				}
			} else {
				resultQuery.add(string);
			}
		}

		return resultQuery;
	} // end of function parseQuery()

	/**
	 * Match strings from left to right one by one
	 * 
	 * @param s1
	 *            - String 1
	 * @param s2
	 *            - String 2
	 * @return boolean: two string are same or not
	 */
	public static boolean matchQuery(String s1, String s2) {

		int minLength = Math.min(s1.length(), s2.length());

		s1 = s1.substring(0, minLength);
		s2 = s2.substring(0, minLength);

		return s1.equals(s2);
	} // end of function matchQuery()

	/**
	 * Take query and return a HashSet of keys
	 * 
	 * @param CD
	 *            - CD data
	 * @param OO
	 *            - OO data
	 * @param diseases
	 *            - String array that contain disease queries
	 * @param drugs
	 *            - String array that contain drug queries
	 * @return HashSet: data keys
	 */
	public static HashSet<String> query(NHIRD_CD_Data CD, NHIRD_OO_Data OO, HashSet<String> diseases, HashSet<String> drugs) {

		HashSet<String> resultKeys = new HashSet<String>();

		if (diseases == null) {
			System.out.println("Query drugs");

			for (String index : OO.drugIndex.keySet()) {
				for (String drug : drugs) {
					if (CoreAlg.matchQuery(index, drug)) {
						resultKeys.addAll(OO.drugIndex.get(index));
					}
				}
			}

		} else if (drugs == null) {
			System.out.println("Query diseases");

			for (String index : CD.diseaseIndex.keySet()) {
				for (String disease : diseases) {
					if (CoreAlg.matchQuery(index, disease)) {
						resultKeys.addAll(CD.diseaseIndex.get(index));
					}
				}
			}

		} else {
			System.out.println("Query diseases and drugs");

			HashSet<String> tempDrug = new HashSet<String>();
			HashSet<String> tempDisease = new HashSet<String>();

			for (String index : OO.drugIndex.keySet()) {
				for (String drug : drugs) {
					if (CoreAlg.matchQuery(index, drug)) {
						tempDrug.addAll(OO.drugIndex.get(index));
					}
				}
			}

			for (String index : CD.diseaseIndex.keySet()) {
				for (String disease : diseases) {
					if (CoreAlg.matchQuery(index, disease)) {
						tempDisease.addAll(CD.diseaseIndex.get(index));
					}
				}
			}

			resultKeys.addAll(tempDrug);
			resultKeys.retainAll(tempDisease);
		}

		return resultKeys;
	} // end of function query()

} // end of class CoreAlg