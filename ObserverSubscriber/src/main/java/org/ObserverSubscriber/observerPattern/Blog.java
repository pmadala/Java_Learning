package org.ObserverSubscriber.observerPattern;

import org.ObserverSubscriber.contents.Content;
import org.ObserverSubscriber.feeds.Feed;

/**
 * observable
 * 
 * @author priyambadam
 *
 */
public interface Blog<T extends Content, E extends Feed> {
	enum BlogEvent {
		ADD, DELETE, UPDATE;
	};

	public void register(E observer);

	public void unregister(E observer);

	public void triggerDataChange(T content);

	public void notifyObservers();

	public T getContent();

}
