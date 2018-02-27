package org.Custom_Annotation;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.Custom_Annotation.model.Document;

/**
 * A utility class for reflection operations 
 * @author priyambadam
 *
 */
public class ReflectionUtils {
	public static final Object NESTED_JSONOBJECT_DELIMITER = "|";
	public static final Object NESTED_JSONOBJECT_PACKAGE = "org.Custom_Annotation.model.";

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
	
	/**
	 * A utility method to initialize a object with its class variables 
	 * @param attributeMap A map containing the value of class variables where key would be the class variable name 
	 * @param docClass class object for creating object of 
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 */
	public static Optional<Document> setObjectFields(Map<String, String> attributeMap, Class docClass) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Field[] fields = docClass.getDeclaredFields();
		Document docObj = (Document) docClass.newInstance();
		List<Field> fieldList = Arrays.asList(fields);
		
		createNestedObjects(fieldList, attributeMap, docObj);
		
		for (String key : attributeMap.keySet()) {
			Optional<Field> fieldOptional = fieldList.stream().filter(fieldKey -> fieldKey.getName().equalsIgnoreCase(key)).findFirst();
			if (fieldOptional.isPresent()) {
				Field field = fieldOptional.get();
				field.setAccessible(true);
				try {
					field.set(docObj, attributeMap.get(key));
				} catch (IllegalArgumentException | IllegalAccessException e) {
					System.err.println("Exception on setting value for field : "+ field.getName()+ " in class : "+ docClass.getName());
					System.err.println(e.getMessage());
					e.printStackTrace();
				}
			}
		}
		return Optional.ofNullable(docObj);
	}

	private static void createNestedObjects(List<Field> fieldList, Map<String, String> attributeMap, Document docObj) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		List<String> filteredKeys = attributeMap.keySet().stream().filter(key-> attributeMap.get(key).contains("|")).collect(Collectors.toList());
		for (String key : filteredKeys) {
			Map<String, String> nestedAttributeMap = convertStringToMap(attributeMap.get(key));
			
			String nestedClassName = key.substring(0, 1).toUpperCase() + key.substring(1, key.length());
			Class nestedClass = Class.forName(NESTED_JSONOBJECT_PACKAGE + nestedClassName);
			Object nestedObj = setObjectFields(nestedAttributeMap, nestedClass);
			
			Optional<Field> fieldOptional = fieldList.stream().filter(fieldKey -> fieldKey.getName().equalsIgnoreCase(key)).findFirst();
			if (fieldOptional.isPresent()) {
				Field field = fieldOptional.get();
				field.setAccessible(true);
				try {
					field.set(docObj, nestedObj);
				} catch (IllegalArgumentException | IllegalAccessException e) {
					System.err.println("Exception on setting value for field : "+ field.getName()+ " in class : "+ nestedClass.getName());
					System.err.println(e.getMessage());
					e.printStackTrace();
				}
			}
		}
		
		attributeMap.keySet().removeAll(filteredKeys);
		
	}
	
	/**
	 * Utility methods to convert a String with pattern to a map 
	 * @param input
	 * @return
	 */
	public static Map<String, String> convertStringToMap(String input) {
		Map<String, String> map = new HashMap<String, String>();

		String[] nameValuePairs = input.split("\\"+NESTED_JSONOBJECT_DELIMITER);
		for (String nameValuePair : nameValuePairs) {
			String[] nameValue = nameValuePair.split("=");
			map.put(nameValue[0], nameValue.length > 1 ? nameValue[1] : "");
		}
		return map;
	}
	
	public static String convertMaptoString(Map<String, String> map) {
		StringBuilder stringBuilder = new StringBuilder();
		
		for (String key : map.keySet()) {
			if (stringBuilder.length() > 0) {
				stringBuilder.append(ReflectionUtils.NESTED_JSONOBJECT_DELIMITER);
			}
			String value = map.get(key);

			stringBuilder.append((key != null ? key : ""));
			stringBuilder.append("=");
			stringBuilder.append(value != null ? value : "");

		}
		return stringBuilder.toString();
	}
	
	public static Method getValueForField(String fieldName, Class classInst) throws NoSuchMethodException,
			SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		String methodName = buildGetMethodName(fieldName);
		Method method = classInst.getMethod(methodName, new Class[] {});
		return method;
	}
}
