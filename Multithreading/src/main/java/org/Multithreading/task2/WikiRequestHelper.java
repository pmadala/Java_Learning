package org.Multithreading.task2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A helper class for wiki request response threads
 * 
 * @author priyambadam
 *
 */
public enum WikiRequestHelper {
	INSTANCE;

	/**
	 * parse the response retrieved from the wiki url hit
	 * 
	 * @param con
	 * @param keyWord
	 * @return
	 * @throws IOException
	 */
	public String parseResponse(HttpURLConnection con, String keyWord)  {
		try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			System.out.println("GET request retrieved for keyword " + keyWord);
			return response.toString();
		} catch(IOException e) {
			e.printStackTrace();
			System.err.println("Exception in parseResponse :"+e.getMessage());
		}
		return "";
	}

	/**
	 * A utility method to write the reposes retrieved from wiki json response The
	 * file name should be the "title" from the json response
	 * 
	 * @param extract
	 * @param title
	 */
	public void writeResponseToFile(String extract, String title) {
		// remove special characters from the file name
		Pattern pt = Pattern.compile("[^a-zA-Z0-9]");
		Matcher match = pt.matcher(title);
		while (match.find()) {
			String s = match.group();
			title = title.replaceAll("\\" + s, "");
		}

		File file = new File("output/task2/File_For_KeyWord_" + title + ".txt");

		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
				System.err.println("Exception in creating file writeResponseToFile :"+e.getMessage());
			}
		}

		try (FileOutputStream fop = new FileOutputStream(file)) {
			byte[] contentInBytes = extract.getBytes();
			fop.write(contentInBytes);
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Exception in writeResponseToFile :"+e.getMessage());
		}
		return;
	}
}
