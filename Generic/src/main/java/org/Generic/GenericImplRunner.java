package org.Generic;

import org.Generic.model.TxtFile;
/**
 * Implementation of main method
 * @author priyambadam
 *
 */
public class GenericImplRunner {
	public static void main(String[] args) throws Exception {
		Object sortType = GenericUtils.getEnumValueByType("SortType", 1);
		if (sortType == SortType.ASC) {
			System.out.println("Enum object found");
		}
		
		Object fileType = GenericUtils.getEnumValueByType("FileTypes", new TxtFile()).toString();
		if (FileTypes.txt.toString().equals(fileType)) {
			System.out.println("Enum object found");
		}
		
		Object stringType = GenericUtils.getEnumValueByType("StringEnum", "five").toString();
		if (StringEnum.FIVE.getValue().equals(stringType)) {
			System.out.println("Enum object found");
		}
		
		Object dataType = GenericUtils.getEnumValueByType("DataTypes", 1.0f).toString();
		if (DataTypes.FLOATVALUE.toString().equals(dataType)) {
			System.out.println("Enum object found");
		}
	}
}
