package org.Multithreading.task2;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.function.Consumer;
import java.util.function.Supplier;

import org.Multithreading.task2.Threads.KeyWordRepository;
import org.Multithreading.task2.Threads.ParserCallable;
import org.apache.commons.lang3.StringUtils;

/**
 * A helper class for the executor to do auxiliary work like reading properties or starting threads 
 * @author priyambadam
 *
 */
public class TaskExecutorHelper {

	/**
	 * Reads the property file for getting wiki base URL and returns it
	 * @return
	 * @throws IOException
	 */
	public static String readWikiURLFromPropertyFile() throws IOException {

		Properties wikiURLproperties = loadProperties("wikiURL.properties");
		Enumeration keys;
		keys = wikiURLproperties.propertyNames();
		String wikiBaseUrlFromProperty = "";
		while (keys.hasMoreElements()) {
			wikiBaseUrlFromProperty = (String) keys.nextElement();
		}
		return wikiBaseUrlFromProperty;
	}

	/**
	 * Reads from property file and start parse threads based on teh input from property file  
	 * @param properties
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	public static void readFilesAndfetchKeywords(Properties properties)
			throws InterruptedException, ExecutionException {
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
		Enumeration keys = properties.propertyNames();
		while (keys.hasMoreElements()) {
			String key = (String) keys.nextElement();
			String value = properties.getProperty(key);
			ParserCallable callable = new ParserCallable(key, value);
			KeyWordRepository.getKeyWords().addAll(executor.submit(callable).get());
		}
	}

	/**
	 * Loads properties written in a file in disk store
	 * @param propertyFile
	 * @return
	 * @throws IOException
	 */
	public static Properties loadProperties(String propertyFile) throws IOException {
		File file = new File(propertyFile);
		FileInputStream fileInput = new FileInputStream(file);
		Properties properties = new Properties();
		properties.load(fileInput);
		fileInput.close();
		return properties;
	}

	/**
	 * Iterator thought the keyword repository and store their response from wiki in
	 * files
	 * 
	 * @param wikiBaseUrl
	 */
	public static void requestResponseWithWiki(String wikiBaseUrl) {
		Set<String> list = KeyWordRepository.getKeyWords();

		Iterator iterator = list.iterator();
		while (iterator.hasNext()) {
			String keyWord = (String) iterator.next();

			if (StringUtils.isNotBlank(keyWord)) {
				Supplier supplier = new WikiRequestSupplier(keyWord, wikiBaseUrl);
				String response = (String) supplier.get();

				Consumer consumer = new WikiResponseConsumer();
				consumer.accept(response);
			}
		}
	}
	
	/**
	 * Reads the property file for getting csv file location
	 * @return
	 * @throws IOException
	 */
	public static String getCSVfielLocationFromproperties() throws IOException {

		Properties csvFileProperties = loadProperties("CSVfileLocation.properties");
		Enumeration keys;
		keys = csvFileProperties.propertyNames();
		String locationCSVFile = "";
		while (keys.hasMoreElements()) {
			locationCSVFile = (String) keys.nextElement();
		}
		return locationCSVFile;
	}

}
