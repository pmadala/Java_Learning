package org.ObserverSubscriber.observerPattern;

import org.ObserverSubscriber.contents.Content;
import org.ObserverSubscriber.feeds.Feed;

/**
 * A type defining all kind of contents in Blog item
 * These are the observable in this application 
 * @author priyambadam
 *
 */
public interface BlogItem<T extends Content, E extends Feed> {
	
	public void register(E observer);

	public void unregister(E observer);

	public void triggerDataChange(T content);

	public void notifyObservers();

	public T getContent();

}
