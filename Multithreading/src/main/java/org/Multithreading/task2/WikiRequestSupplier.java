package org.Multithreading.task2;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.function.Supplier;

import org.Multithreading.IConstants;
import org.apache.commons.lang3.StringUtils;

/**
 * A supplier class responsible for creating HTTP request to wiki provided with
 * a keyword
 * 
 * @author priyambadam
 *
 */
public class WikiRequestSupplier implements Supplier<String> {

	private static final String USER_AGENT = "Mozilla/5.0";
	private String keyWord;
	private static String wikiBaseUrl;

	public WikiRequestSupplier(String keyword, String wikiBaseUrl) {
		this.keyWord = keyword;
		if (StringUtils.isBlank(this.wikiBaseUrl))
			this.wikiBaseUrl = wikiBaseUrl;
	}

	@Override
	public String get() {

		String url = "";
		try {
			url = wikiBaseUrl + URLEncoder.encode( keyWord, "UTF-8" );
		} catch (UnsupportedEncodingException e2) {
			System.err.println("URL encoding failed"+ e2.getMessage());
		}  

		URL obj = null;
		try {
			obj = new URL(url);
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
			System.err.println(e1.getMessage());
		}
		HttpURLConnection con;
		try {
			con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("User-Agent", USER_AGENT);
			int responseCode = con.getResponseCode();
			System.out.println("GET Response Code :: " + responseCode);
			if (responseCode == HttpURLConnection.HTTP_OK) { // success
				return WikiRequestHelper.INSTANCE.parseResponse(con,keyWord);
			} else {
				System.out.println("GET request not worked " + keyWord);
				return IConstants.WIKI_REQUEST_UNSUCCESSFUL;
			}

		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Exception in executing request in WikiRequestSupplier :"+e.getMessage());
		}
		return IConstants.WIKI_REQUEST_UNSUCCESSFUL;
	}

	
}
