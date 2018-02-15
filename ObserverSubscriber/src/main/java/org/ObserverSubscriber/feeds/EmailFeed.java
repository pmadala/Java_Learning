package org.ObserverSubscriber.feeds;

import org.ObserverSubscriber.observerPattern.Blog;

public class EmailFeed extends AbstractFeed{

	@Override
	public void update(Blog blog) {
		System.out.println("Email Feed updating ->" +  getObserverFromList(blog).getContent().getData());
	}
}
