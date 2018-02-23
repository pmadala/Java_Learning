package org.Multithreading.task2;

import java.util.Iterator;
import java.util.Map;
import java.util.function.Consumer;

import org.Multithreading.IConstants;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * A consumer class responsible for extracting the json response provided by the
 * supplier and write the "extract" to file .
 * 
 * @author priyambadam
 *
 */
public class WikiResponseConsumer implements Consumer<String> {

	@Override
	public void accept(String response) {
		if (IConstants.WIKI_REQUEST_UNSUCCESSFUL.equalsIgnoreCase(response)) {
			return;
		}
		JSONObject obj = new JSONObject(response);
		String extract = "";
		String title = "";
		try {
			if (obj.has("query")) {
				JSONObject query = (JSONObject) obj.get("query");
				if (query.has("pages")) {
					JSONObject pages = (JSONObject) query.get("pages");
					Map<String, Object> pageMap = pages.toMap();
					Iterator<String> iterator = pages.keys();
					while (iterator.hasNext()) {
						String key = iterator.next();
						Map<String, String> mapEntry = (Map<String, String>) pageMap.get(key);
						extract = StringUtils.isBlank(mapEntry.get("extract")) ? "" : mapEntry.get("extract");
						title = mapEntry.get("title");
					}
				}
			}
		} catch (JSONException ex) {
			ex.printStackTrace();
			System.err.println("Exception occured in JSON parsing "+ex.getMessage());
		}

		WikiRequestHelper.INSTANCE.writeResponseToFile(extract, title);

		System.out.println("Response written for keyword : " + title);
		return;
	}
}
