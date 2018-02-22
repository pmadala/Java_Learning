package org.ObserverSubscriber.observerPattern;

import org.ObserverSubscriber.BlogItemType;

/**
 * A class representing white paper blog 
 * @author priyambadam
 *
 */
public class WhitePaperBlog extends AbstractBlogItem {

	public <T> WhitePaperBlog(T content) {
		super(content);
		blogItemType = BlogItemType.WHITE_PAPER;
	}

}
