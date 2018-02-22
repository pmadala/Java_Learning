package org.ObserverSubscriber.observerPattern;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.ObserverSubscriber.BlogItemType;
import org.ObserverSubscriber.contents.Content;
import org.ObserverSubscriber.feeds.Feed;

/**
 * A skeletal representation of Blog type
 * @author priyambadam
 *
 * @param <T> Content type
 * @param <E> Observer feed
 */
public abstract class AbstractBlogItem<T, E> implements BlogItem {
	protected List<E> observers = new ArrayList<E>();
	protected T content;
	protected BlogItemType blogItemType; 

	public AbstractBlogItem(T content) {
		this.content = content;
	}
	
	public void register(Feed observer) {
		observers.add((E) observer);
		BlogImplementation.getInstance().notifyObservers(observers, blogItemType, EventType.ADD);
	}

	public void unregister(Feed observer) {
		observers.remove(observer);		
		BlogImplementation.getInstance().notifyObservers(observers, blogItemType, EventType.DELETE);
	}
	
	/**
	 * A method to change to the state of the data in the content of the blog
	 * If there are no content set for the article then, content would be assigned to the article
	 * Otherwise, the content is added to the existing content in the article 
	 * 
	 * After all the above operations it will notify all the observers listed in the observable blog  
	 *  
	 */
	public void triggerDataChange(Content content) {
		if (!Objects.isNull(this.content)) {
			this.content = (T) content;
		} else {
			((Content) getContent()).setData(content.getData());
		}
		notifyObservers();	
		BlogImplementation.getInstance().notifyObservers(observers, blogItemType, EventType.MODIFY);
	}

	/**
	 * A method to notify all the observers listed in the observable blog
	 */
	public void notifyObservers() {
		observers.forEach(s-> ((Feed) s).update(this));		
	}
	
	public List<E> getObservers() {
		return observers;
	}

	public void setObservers(List<E> observers) {
		this.observers = observers;
	}

	public Content getContent() {
		return (Content) content;
	}

	public void setContent(T content) {
		this.content = content;
	}
}
