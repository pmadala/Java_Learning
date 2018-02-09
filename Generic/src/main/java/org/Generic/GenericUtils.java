package org.Generic;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

public class GenericUtils {
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