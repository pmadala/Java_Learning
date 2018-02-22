package org.ObserverSubscriber.observerPattern;

import org.ObserverSubscriber.BlogItemType;

/**
 * A class representing comment blog
 * @author priyambadam
 *
 */
public class CommentBlog extends AbstractBlogItem {
	public CommentBlog nestedComment;
	
	public <T> CommentBlog(T content) {
		super(content);
		blogItemType = BlogItemType.COMMENT;
	}
	
	/**
	 * Add nested comment blog to a existing comment blog 
	 * After adding the nested comments all the listed observers will be notified  
	 * 
	 * @param comment - nested comment object 
	 */
	public void addNestedComment (CommentBlog comment) {
		nestedComment = comment;
		getContent().setData("\nNested Comment Added");
		notifyObservers();
	}
}
