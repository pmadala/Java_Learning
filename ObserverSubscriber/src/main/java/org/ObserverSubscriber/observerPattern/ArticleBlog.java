package org.ObserverSubscriber.observerPattern;

import java.util.Objects;

public class ArticleBlog extends AbstractBlog{
	
	public <T> ArticleBlog(T content) {
		super(content);
	}
	
	public void register(Feed observer) {
		observers.add(observer);		
	}

	public void unregister(Feed observer) {
		observers.remove(observer);		
	}

	public void triggerDataChange(ContentType content) {
		if (Objects.isNull(this.content)) {
			this.content = content;
		} else {
			((ContentType) getContent()).setData(content.getData());
		}
		notifyObservers();	
	}

	public void notifyObservers() {
		observers.forEach(s-> ((Feed) s).update());		
	}

}
