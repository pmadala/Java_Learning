package org.ObserverSubscriber.observerPattern;

public class ArticleBlog extends AbstractBlog{
	
	public <T> ArticleBlog(T content) {
		super(content);
	}

}
