package org.ObserverSubscriber.feeds;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.ObserverSubscriber.observerPattern.Blog;

public abstract class AbstractFeed implements Feed{
	protected List<Blog> blogs;
	
	public void setObservable(Blog blog) {
		if (Objects.isNull(blogs))
			blogs = new ArrayList<>();
		this.blogs.add(blog);
	}
	
	protected Blog getObserverFromList(Blog blog) {
		return blogs.stream().filter(o -> o.getContent().equals(blog.getContent())).findFirst().get();
	}
}
