package org.ObserverSubscriber.feeds;

import org.ObserverSubscriber.observerPattern.AbstractBlog;
import org.ObserverSubscriber.observerPattern.Blog;

public interface Feed {
	
	public void setObservable(Blog blog);
	
	public void update(Blog blog);

}
