package org.ObserverSubscriber.feeds;

import org.ObserverSubscriber.BlogItemType;
import org.ObserverSubscriber.observerPattern.BlogItem;
import org.ObserverSubscriber.observerPattern.EventType;

/**
 * A type defining all kind of contents in Feed
 * These are the observers in this application 
 * @author priyambadam
 *
 */
public interface Feed {
	
	public void addObservable(BlogItem blog);
	
	/**
	 * A method representing the tasks to be performed once a observable is changed by its behavior 
	 * @param blog - instance of observable changed 
	 */
	public void update(BlogItem blog);

	public void updateForBlog(BlogItemType blogItemType, EventType eventType);

}
