package org.ObserverSubscriber.observerPattern;

import org.ObserverSubscriber.BlogItemType;

/**
 * A class representing Article Blog
 * @author priyambadam
 *
 */

public class ArticleBlog extends AbstractBlogItem{
	
	public <T> ArticleBlog(T content) {
		super(content);
		blogItemType = BlogItemType.ARTICLE;
	}

}
