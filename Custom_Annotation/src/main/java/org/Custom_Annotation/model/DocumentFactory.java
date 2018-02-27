package org.Custom_Annotation.model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;

import org.Custom_Annotation.CustomAnnotationException;
import org.Custom_Annotation.ReflectionUtils;

import com.google.gson.JsonObject;

/**
 * A singleton factory class for document creation
 * 
 * @author priyambadam
 *
 */
public enum DocumentFactory {
	INSTANCE;

	/**
	 * Create a document from a json Object The type of the document is determined
	 * by file name
	 * 
	 * @param jsonObject
	 * @param fileName
	 * @return
	 * @throws CustomAnnotationException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 */
	public Optional<Document> getDocumentInstance(JsonObject jsonObject, String fileName)
			throws CustomAnnotationException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		Class docClass = getClassNameFromFileName(fileName);
		Map<String, String> attributeMap = createAttributeMap(jsonObject);
		Optional<Document> document = ReflectionUtils.setObjectFields(attributeMap, docClass);
		return document;
	}

	private Map<String, String> createAttributeMap(JsonObject jsonObject) {
		Map<String, String> atrributeMap = new HashMap<>();
		Iterator keys = jsonObject.keySet().iterator();

		while (keys.hasNext()) {
			String key = (String) keys.next();
			String value = "";
			if (jsonObject.get(key).isJsonObject()) {
				Map<String, String> nestedAtrributeMap = createAttributeMap((JsonObject) jsonObject.get(key));
				value = ReflectionUtils.convertMaptoString(nestedAtrributeMap);
			} else {
				value = jsonObject.get(key).getAsString();
			}
			atrributeMap.put(key, value);
		}
		return atrributeMap;
	}

	private Class getClassNameFromFileName(String fileName) throws CustomAnnotationException {
		Class<? extends Document> docClass;
		String[] keys = { "Aadhar", "Pan", "Statement" };
		String[] values = { "conditionA", "conditionB", "conditionC" };

		String match = "default";
		for (int i = 0; i < keys.length; i++) {
			if (fileName.contains(keys[i])) {
				match = values[i];
				break;
			}
		}

		switch (match) {
		case "conditionA":
			docClass = Aadhar.class;
			break;
		case "conditionB":
			docClass = PanCard.class;
			break;
		case "conditionC":
			docClass = BankStatement.class;
			break;
		default:
			throw new CustomAnnotationException("Unknow document in the set.");
		}
		return docClass;
	}

}
