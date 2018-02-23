package org.Multithreading.task2;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.Multithreading.task2.Threads.KeyWordRepository;

/**
 * A class responsible for reading all the keywords from given set of input
 * files and extracting their details from wiki . Post the above task the
 * response needs to be written to a physical file.
 * 
 * @author priyambadam
 *
 */
public class TaskExecutor {
	private static final int FIXED_NUMBER_THREAD = 10;

	public static void main(String args[]) throws InterruptedException, IOException, ExecutionException {
		TaskExecutorHelper helper = new TaskExecutorHelper();
		
		Properties properties = helper.loadProperties("test.properties");
		
		Enumeration keys;
		helper.readFilesAndfetchKeywords(properties);

		Thread.sleep(5000);
		System.out.println(KeyWordRepository.getKeyWords().size());

		final String wikiBaseUrl = helper.readWikiURLFromPropertyFile();
		
		Long startTime = System.currentTimeMillis();
		ExecutorService service = Executors.newFixedThreadPool(FIXED_NUMBER_THREAD);
		for (int i = 0; i < FIXED_NUMBER_THREAD; i++) {
			service.execute(new Runnable() {

				@Override
				public void run() {
					helper.requestResponseWithWiki(wikiBaseUrl);

				}
			});
		}
		Long endTime = System.currentTimeMillis();
		System.out.println("Time spent in seconds : " + (endTime - startTime / 1000));
	}

}
