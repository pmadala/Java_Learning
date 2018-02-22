package org.ObserverSubscriber.observerPattern;

import org.ObserverSubscriber.BlogItemType;

/**
 * A class representing case study blog
 * @author priyambadam
 *
 */
public class CaseStudyBlog extends AbstractBlogItem{
	
	public <T> CaseStudyBlog(T content) {
		super(content);
		blogItemType = BlogItemType.CASE_STUDY;
	}

}
