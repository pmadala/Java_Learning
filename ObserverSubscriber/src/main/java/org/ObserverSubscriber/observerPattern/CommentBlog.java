package org.ObserverSubscriber.observerPattern;

public class CommentBlog extends AbstractBlog {
	public CommentBlog nestedComment;
	
	public <T> CommentBlog(T content) {
		super(content);
	}
	
	public void addNestedComment (CommentBlog comment) {
		nestedComment = comment;
		getContent().setData("\nNested Comment Added");
		notifyObservers();
	}
}
