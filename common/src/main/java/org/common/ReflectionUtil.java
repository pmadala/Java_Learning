package org.common;

/**
 * Utility class for use with Reflection
 * @author priyambadam
 *
 */
public class ReflectionUtil {

	/**
	 * Build the get method name, according to the attribute name
	 * 
	 * @param fieldName
	 * @return get attribute method
	 */
	public static String buildGetMethodName(String fieldName) {
		StringBuilder methodName = new StringBuilder("get");
		methodName.append(fieldName.substring(0, 1).toUpperCase());
		methodName.append(fieldName.substring(1, fieldName.length()));
		return methodName.toString();
	}

	/**
	 * Build the name of the set method, according to the attribute name
	 * 
	 * @param fieldName
	 * @return set attribute method
	 */
	public static String buildSetMethodName(String fieldName) {
		StringBuilder methodName = new StringBuilder("set");
		methodName.append(fieldName.substring(0, 1).toUpperCase());
		methodName.append(fieldName.substring(1, fieldName.length()));
		return methodName.toString();
	}

}