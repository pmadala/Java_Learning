package org.Multithreading.task2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.function.Supplier;

import org.Multithreading.IConstants;
import org.Multithreading.task2.Threads.KeyWordRepository;

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

	public WikiRequestSupplier(String keyword) {
		this.keyWord = keyword;
	}

	@Override
	public String get() {

		String url = "https://en.wikipedia.org/w/api.php?format=json&action=query&prop=extracts&exintro=&explaintext=&titles="
				+ keyWord;

		URL obj = null;
		try {
			obj = new URL(url);
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}
		HttpURLConnection con;
		try {
			con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("User-Agent", USER_AGENT);
			int responseCode = con.getResponseCode();
			System.out.println("GET Response Code :: " + responseCode);
			if (responseCode == HttpURLConnection.HTTP_OK) { // success
				BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();

				System.out.println("GET request retrieved for keyword " + keyWord);
				return response.toString();
			} else {
				System.out.println("GET request not worked " + keyWord);
				return IConstants.WIKI_REQUEST_UNSUCCESSFUL;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return IConstants.WIKI_REQUEST_UNSUCCESSFUL;
	}

}
