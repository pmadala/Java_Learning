package org.ObserverSubscriber.observerPattern;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractBlog<T, E> implements Blog {
	protected List<E> observers = new ArrayList<E>();
	protected T content;

	public AbstractBlog(T content) {
		this.content = content;
	}
	
	public List<E> getObservers() {
		return observers;
	}

	public void setObservers(List<E> observers) {
		this.observers = observers;
	}

	public ContentType getContent() {
		return (ContentType) content;
	}

	public void setContent(T content) {
		this.content = content;
	}
}
