package org.ObserverSubscriber.feeds;

import org.ObserverSubscriber.observerPattern.Blog;

/**
 * A type defining all kind of contents in Feed
 * @author priyambadam
 *
 */
public interface Feed {
	
	public void addObservable(Blog blog);
	
	/**
	 * A method representing the tasks to be performed once a observable is changed by its behavior 
	 * @param blog - instance of observable changed 
	 */
	public void update(Blog blog);

}
