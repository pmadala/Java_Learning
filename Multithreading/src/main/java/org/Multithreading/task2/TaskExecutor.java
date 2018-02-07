package org.Multithreading.task2;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.function.Consumer;
import java.util.function.Supplier;

import org.Multithreading.task2.Threads.CompaniesThread;
import org.Multithreading.task2.Threads.JavaKeyWordThread;
import org.Multithreading.task2.Threads.KeyWordRepository;
import org.Multithreading.task2.Threads.ProgrammingLanguageThread;
import org.Multithreading.task2.WikiRequestSupplier;
import org.Multithreading.task2.WikiResponseConsumer;
import org.apache.commons.lang3.StringUtils;

/**
 * A class responsible for redaing all the keywords from given set of input
 * files and extracting their details from wiki . Post the above task the
 * response needs to be written to a physical file.
 * 
 * @author priyambadam
 *
 */
public class TaskExecutor {
	public static void main(String args[]) throws InterruptedException {
		Runnable thread1 = new JavaKeyWordThread();
		Runnable thread2 = new ProgrammingLanguageThread();
		Runnable thread3 = new CompaniesThread();

		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
		executor.execute(thread1);
		executor.execute(thread2);
		executor.execute(thread3);

		Thread.sleep(5000);
		System.out.println(KeyWordRepository.getKeyWords().size());

		// TODO: make multi threading work here
		Long startTime = System.currentTimeMillis();
		ExecutorService service = Executors.newFixedThreadPool(10);
		for (int i = 0; i < 10; i++) {
			service.execute(new Runnable() {

				@Override
				public void run() {
					requestResponseWithWiki();

				}
			});
		}
		Long endTime = System.currentTimeMillis();
		System.out.println("Time spent in seconds : " + (endTime - startTime / 1000));
	}

	/**
	 * Iterater throught the keyword repository and store their response from wiki
	 * in files
	 */
	private static void requestResponseWithWiki() {
		Set<String> list = KeyWordRepository.getKeyWords();

		Iterator iterator = list.iterator();
		while (iterator.hasNext()) {
			String keyWord = "";

			keyWord = getKeyword(iterator);

			if (StringUtils.isNotBlank(keyWord)) {
				Supplier supplier = new WikiRequestSupplier(keyWord);
				String response = (String) supplier.get();

				Consumer consumer = new WikiRespsonseConsumer();
				consumer.accept(response);
			}
		}

	}

	private static synchronized String getKeyword(Iterator iterator) {
		String keyWord = (String) iterator.next();
		iterator.remove();
		return keyWord;
	}
}
