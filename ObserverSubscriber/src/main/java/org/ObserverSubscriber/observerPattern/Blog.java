package org.ObserverSubscriber.observerPattern;

import java.util.List;

import org.ObserverSubscriber.BlogItemType;
import org.ObserverSubscriber.feeds.Feed;

/**
 * An interface representing the type for a blog which contains several blog items 
 * 
 * Observers are enrolled for notification based on blog item type and event type 
 * 
 * 
 * @author priyambadam
 *
 * @param <T>
 * @param <E>
 */
public interface Blog<T extends BlogItemType, E extends Feed> {
	
	public void register(E observer, T blogItemType, EventType eventType);
	
	public void unregister(E observer, T blogItemType, EventType eventType);

	public void triggerDataChange(BlogItemType blogItemType, EventType eventType);

	public void notifyObservers(List<Feed> notifiableObserver, BlogItemType blogItemType, EventType eventType);

	
}
