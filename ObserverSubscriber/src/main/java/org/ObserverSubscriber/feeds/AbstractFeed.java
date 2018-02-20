package org.ObserverSubscriber.feeds;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.ObserverSubscriber.BlogItemType;
import org.ObserverSubscriber.observerPattern.BlogItem;
import org.ObserverSubscriber.observerPattern.EventType;

/**
 * A skeletal representation of the feed type
 * @author priyambadam
 *
 */
public abstract class AbstractFeed implements Feed{
	protected List<BlogItem> blogs;
	
	/**
	 * List of blogs that feed observer is observing
	 */
	public void addObservable(BlogItem blog) {
		if (Objects.isNull(blogs))
			blogs = new ArrayList<>();
		this.blogs.add(blog);
	}
	
	protected BlogItem getObserverFromList(BlogItem blog) {
		return blogs.stream().filter(o -> o.getContent().equals(blog.getContent())).findFirst().get();
	}
}
