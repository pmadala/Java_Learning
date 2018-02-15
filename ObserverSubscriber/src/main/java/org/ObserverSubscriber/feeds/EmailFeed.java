package org.ObserverSubscriber.feeds;

import org.ObserverSubscriber.observerPattern.Blog;

/**
 * A class representing Email feed
 * @author priyambadam
 *
 */
public class EmailFeed extends AbstractFeed{

	@Override
	public void update(Blog blog) {
		System.out.println("Email Feed updating ->" +  getObserverFromList(blog).getContent().getData());
	}
}
