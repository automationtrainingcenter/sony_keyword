package banking.keyworddriven;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Driver {
	public static void main(String[] args) {
		// create Keywords class object to call the methods of that class
		Keywords keywords = new Keywords();
		Method[] methods = keywords.getClass().getMethods();
		// create an object ExcelHelper to read data from testcases sheet
		ExcelHelper tcdoc = new ExcelHelper();
		tcdoc.openExcel("resources", "input.xlsx", "testcases");
		// create an object ExcelHelper to read data from teststeps sheet
		ExcelHelper tsdoc = new ExcelHelper();
		tsdoc.openExcel("resources", "input.xlsx", "teststeps");

		// write a loop which will iterate over number of rows in testcases doc
		for (int i = 1; i <= tcdoc.rowCount(); i++) {
			String tcd_tcname = tcdoc.readData(i, 1);
			String runMode = tcdoc.readData(i, 2);
//			System.out.println(tcd_tcname+"\t"+runMode);

			if (runMode.equalsIgnoreCase("yes")) {
				// write a loop which will iterate over number of rows in teststeps doc
				for (int j = 1; j <= tsdoc.rowCount(); j++) {
					String tsd_tcname = tsdoc.readData(j, 0);
					// compare test case doc test case name with test step doc test case name
					if(tcd_tcname.equalsIgnoreCase(tsd_tcname)) {
						// now get test step name, loc type, loc value, action, test data
						String tsname = tsdoc.readData(j, 1);
//						System.out.println(tsname);
						String locType = tsdoc.readData(j, 2);
						String locValue = tsdoc.readData(j, 3);
						String action = tsdoc.readData(j, 4);
						String testData = tsdoc.readData(j, 5);
						for(Method method : methods) {
							if(method.getName().equals(action)) {
								try {
									method.invoke(keywords, locType, locValue, testData);
									break; // terminates methods loop
								} catch (Exception e) {
									System.out.println(e.getMessage());
								}
							}
						}
					}
				}
			}
		}
		tsdoc.closeExcel();
		tcdoc.closeExcel();
	}

}
