package org.ObserverSubscriber.feeds;

import org.ObserverSubscriber.observerPattern.Blog;

public class OnScreenFeed extends AbstractFeed{

	@Override
	public void update(Blog blog) {
		System.out.println("On Screen Feed updating ->" +  getObserverFromList(blog).getContent().getData());
	}

}
