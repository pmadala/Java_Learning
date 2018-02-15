package org.ObserverSubscriber.observerPattern;

/**
 * observable
 * 
 * @author priyambadam
 *
 */
public interface Blog<T extends ContentType, E extends Feed> {
	enum BlogEvent {
		ADD, DELETE, UPDATE;
	};

	public void register(E observer);

	public void unregister(E observer);

	public void triggerDataChange(T content);

	public void notifyObservers();

	public T getContent();

}
