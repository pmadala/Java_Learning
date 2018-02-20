package org.ObserverSubscriber.feeds;

import org.ObserverSubscriber.BlogItemType;
import org.ObserverSubscriber.observerPattern.BlogItem;
import org.ObserverSubscriber.observerPattern.EventType;

/**
 * A class representing On Screen feed
 * @author priyambadam
 *
 */
public class OnScreenFeed extends AbstractFeed{

	@Override
	public void update(BlogItem blog) {
		System.out.println("On Screen Feed updating ->" +  getObserverFromList(blog).getContent().getData());
	}

	@Override
	public void updateForBlog(BlogItemType blogItemType, EventType eventType) {
		System.out.println("On Sceen Feed updating -> event happened : " + eventType + " on blog item : "+ blogItemType);
		
	}
}
