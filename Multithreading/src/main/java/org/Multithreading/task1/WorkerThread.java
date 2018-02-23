package org.Multithreading.task1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.Multithreading.IConstants;

import com.sun.tools.sjavac.Log;

/**
 * A thread class for splitting teh list of lines into a number of threashold lines  
 * 
 * @author priyambadam
 *
 */
public class WorkerThread implements Runnable {
	
	private static List<String> lines;
	private String name ;
	private static volatile int count;
	private AtomicInteger atomicInteger = new AtomicInteger();
	
	public WorkerThread(String name) {
		this.name = name;
	}

	public void run() {
		System.out.println(Thread.currentThread().getName() + " Start. " );
		List<String> extractLines = extractLines();
		if (!extractLines.isEmpty()) {
			File file = createAndWriteToFile(extractLines);
			System.out.println("File writing complete by thread . " + Thread.currentThread().getName());
		}
		System.out.println(Thread.currentThread().getName() + " End.");
	}

	private File createAndWriteToFile(List<String> extractLines) {
		atomicInteger.incrementAndGet();
		File file = new File("output/task1/File_By_" + this.name +"_" +atomicInteger.get()+ ".txt");

		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		try (FileOutputStream fop = new FileOutputStream(file)){
			for (String content : extractLines) {
				byte[] contentInBytes = content.getBytes();
		
				fop.write(contentInBytes);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return file;
	}

	private List<String> extractLines() {
		List<String> list = new ArrayList();
		BlockingQueue queue = new ArrayBlockingQueue(IConstants.MAX_NUMBER_OF_LINES_IN_FILE);
		if (!lines.isEmpty()) {
			synchronized (queue) {
				for (int i = 0; i < IConstants.MAX_NUMBER_OF_LINES_IN_FILE; i++) {
					queue.offer(lines.get(count));
					lines.remove(count);
					count++;
				}
				list = (List<String>) queue.stream().collect(Collectors.toList());
			}
		}
		return list;
	}

	public static List<String> getLines() {
		return lines;
	}

	public static void setLines(List<String> lines) {
		WorkerThread.lines = lines;
	}
}
