package org.ObserverSubscriber.observerPattern;

/**
 * A class representing Article Blog
 * @author priyambadam
 *
 */

public class ArticleBlog extends AbstractBlogItem{
	
	public <T> ArticleBlog(T content) {
		super(content);
	}

}
