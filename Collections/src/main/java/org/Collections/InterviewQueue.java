package org.Collections;

import java.util.concurrent.PriorityBlockingQueue;

import jdk.internal.jline.internal.Log;
import model.Candidate;
/**
 * A priority blocking queue representing the interview pool
 * Singleton design pattern for having one instance of interview pool through out the runtime  
 * @author priyambadam
 *
 */
public class InterviewQueue extends PriorityBlockingQueue<Candidate> {

	private static InterviewQueue instanceQueue;
	private static final int  MAX_QUEUE_LIMIT =1;

	private InterviewQueue(int maxQueueLimit) {
		super(maxQueueLimit);
	}

	public static synchronized InterviewQueue getInstance() {
		if (instanceQueue == null) {
			synchronized (InterviewQueue.class) {
				if (instanceQueue == null) {
					instanceQueue = new InterviewQueue(MAX_QUEUE_LIMIT);
				}
			}
		}
		return instanceQueue;
	}

	/**
	 * Utility method to print all the elements in the queue according to their priority . 
	 */
	public void printInterviewCandidateSequence() {
		while(!instanceQueue.isEmpty()) {
			System.out.println(instanceQueue.poll().print());
		}
	}
}
