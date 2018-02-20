package org.ObserverSubscriber.feeds;

import org.ObserverSubscriber.BlogItemType;
import org.ObserverSubscriber.observerPattern.BlogItem;
import org.ObserverSubscriber.observerPattern.EventType;

/**
 * A class representing pdf feed
 * @author priyambadam
 *
 */
public class PDFFeed extends AbstractFeed{

	@Override
	public void update(BlogItem blog) {
		System.out.println("PDF Feed updating ->" +  getObserverFromList(blog).getContent().getData());
	}
	
	@Override
	public void updateForBlog(BlogItemType blogItemType, EventType eventType) {
		System.out.println("PDF Feed updating -> event happened : " + eventType + " on blog item : "+ blogItemType);
		
	}

}
