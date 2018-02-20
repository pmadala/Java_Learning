package org.ObserverSubscriber.observerPattern;

/**
 * A class representing white paper blog 
 * @author priyambadam
 *
 */
public class WhitePaperBlog extends AbstractBlogItem {

	public <T> WhitePaperBlog(T content) {
		super(content);
	}

}
