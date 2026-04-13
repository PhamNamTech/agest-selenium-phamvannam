package Railway;

import Constant.Constant;

public class TableHelper {
	private TableHelper() {
		
	}

	/**
	 * Generates XPath to find table row matching values in two specific columns
	 * 
	 * Use case: Find ticket row where Depart="Nha Trang" AND Arrive="Huế"
	 * 
	 * @param col1Index Index of first column (1-based)
	 * @param col1Value Expected value in first column
	 * @param col2Index Index of second column (1-based)
	 * @param col2Value Expected value in second column
	 * @return XPath string for the matching row
	 * 
	 * @example getRowBy2Cols(2, "Nha Trang", 3, "Huế")
	 *          Returns XPath finding row with col2="Nha Trang" and col3="Huế"
	 *          Format: //tr[td[2][normalize-space()='Nha Trang'] and td[3][normalize-space()='Huế']]
	 */
	public static String getRowBy2Cols(int col1Index, String col1Value,int col2Index, String col2Value) {
		String xpath = String.format(Constant.ROW_BY_2_COLS, col1Index, col1Value, col2Index, col2Value);
		return xpath;
	}
	
}
