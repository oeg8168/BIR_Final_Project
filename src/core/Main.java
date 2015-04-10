package core;

import java.io.File;
import java.util.HashSet;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableWorkbook;
import dataIO.NHIRD_CD_Data;
import dataIO.NHIRD_OO_Data;

public class Main {

	public static void main(String[] args) {
		
		System.out.println("".isEmpty());
		System.out.println("  ".isEmpty());
		
		try {
			WritableWorkbook workbook = Workbook.createWorkbook(new File("out.xls"));
			
			workbook.createSheet("result", 0);
			
			workbook.getSheet(0).addCell(new Label(0,0,"ID"));
			workbook.getSheet(0).addCell(new Label(1,0,"ID_SEX"));
			workbook.getSheet(0).addCell(new Label(2,0,"ID_BIRTHDAY"));
			workbook.getSheet(0).addCell(new Label(3,0,"ACODE_ICD9_1"));
			workbook.getSheet(0).addCell(new Label(4,0,"ACODE_ICD9_2"));
			workbook.getSheet(0).addCell(new Label(5,0,"ACODE_ICD9_3"));
			workbook.getSheet(0).addCell(new Label(6,0,"DRUG_NO"));
			workbook.getSheet(0).addCell(new Label(7,0,"DRUG_DAY"));
			workbook.getSheet(0).addCell(new Label(8,0,"TOTAL_QTY"));
			workbook.getSheet(0).addCell(new Label(9,0,"FUNC_DATE"));
			
			
			workbook.write();
			workbook.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// String[] a = {"123","ADD","456","ASD2","AAAA"};
		// Arrays.sort(a);
		//
		// for (String string : a) {
		// System.out.println(string);
		// }
		//
		// for (String string : DataIO.readDrugFile(new
		// File("C:/Users/LISM_OEG/Desktop/DownLoad (1).xls"))) {
		// System.out.println(string);
		// }
		//

		String testInputPath20 = new String("C:/Users/LISM_OEG/Desktop/20KX/EX_CD20KX.DAT");
		String testInputPathCD = new String("C:/Users/LISM_OEG/Desktop/R301_CD2009.DAT");
		String testInputPathOO = new String("C:/Users/LISM_OEG/Desktop/R301_OO2009.DAT");

		@SuppressWarnings("unused")
		NHIRD_CD_Data CDtest = new NHIRD_CD_Data(new File(testInputPath20));

		// for (String key : CDtest.rawDataMap.keySet()) {
		// System.out.println(key);
		// }
		//
		// for (String key : CDtest.encodeKey) {
		// System.out.println(key);
		// }

		NHIRD_CD_Data CD = new NHIRD_CD_Data(new File(testInputPathCD));
		NHIRD_OO_Data OO = new NHIRD_OO_Data(new File(testInputPathOO));

		// OO.indexing();
		// System.out.println(OO.rawDataMap.size());
		// System.out.println(OO.drugIndex.size());
		//
		// CD.indexing();
		// System.out.println(CD.rawDataMap.size());
		// System.out.println(CD.diseaseIndex.size());

		// for (String string : OO.drugIndex.keySet()) {
		// System.out.println(string);
		// }

		// for (String string : CD.diseaseIndex.keySet()) {
		// System.out.println(string);
		// }

		//
		// CoreAlg.query(CD, OO, null, new String[]{"A048286335  ",
		// "A034781     "});
		// CoreAlg.query(CD, OO, null, new String[]{"A048286335", "A034781"});

		HashSet<String> test = new HashSet<String>();
		test = CoreAlg.query(CD, OO, CoreAlg.parseQuery("210-220,250"), CoreAlg.parseQuery("A03,"));
		System.out.println();

		CoreAlg.definiteDiagnosis(CD, test, 2);
		
		CoreAlg.sortByOrder(CD, OO, test, new String[] { "ID", "Disease", "Time", "Drug" });
		System.out.println();
		CoreAlg.sortByOrder(CD, OO, test, new String[] { "ID", "Disease", "ID", "Drug" });

		long startTime = System.currentTimeMillis();
		long endTime = System.currentTimeMillis();
		long totTime = endTime - startTime;
		System.out.println("Using Time:" + totTime / 1000f);

	}

} // end of class Main
