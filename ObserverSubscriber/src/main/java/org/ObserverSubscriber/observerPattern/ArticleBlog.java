package org.ObserverSubscriber.observerPattern;

/**
 * A class representing Article Blog
 * @author priyambadam
 *
 */

public class ArticleBlog extends AbstractBlog{
	
	public <T> ArticleBlog(T content) {
		super(content);
	}

}
