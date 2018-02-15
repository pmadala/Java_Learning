package org.ObserverSubscriber.feeds;

import org.ObserverSubscriber.observerPattern.Blog;

/**
 * A class representing pdf feed
 * @author priyambadam
 *
 */
public class PDFFeed extends AbstractFeed{

	@Override
	public void update(Blog blog) {
		System.out.println("PDF Feed updating ->" +  getObserverFromList(blog).getContent().getData());
	}

}
