package org.Generic;

import java.util.Objects;

/**
 * Generic utility class
 * @author priyambadam
 *
 */
public class GenericUtils {
	
	/**
	 * Generic method for getting the enum object by providing the following inputs
	 * All the enum classes needs to be in org.Generic package .
	 * Further package name can be generalized 
	 * 
	 * @param enumName Name of the enum class
	 * @param value value of the enum object 
	 * @return
	 * @throws Exception
	 */
	public static <T,R> Object getEnumValueByType(String enumName, T value) throws Exception {
		Class<?> c = Class.forName("org.Generic."+enumName);
		Object[] objects = c.getEnumConstants();
		Object returnVal = null;
		for(Object obj : objects) {
			if (value.toString().equals(obj.toString())) {
				returnVal =  obj;
			}
		}
		if (Objects.isNull(returnVal))
			throw new Exception("Enum not fund for value : "+ value.toString());
		return returnVal;	
	}
}