package org.ObserverSubscriber;

import java.util.Optional;

import org.ObserverSubscriber.feeds.EmailFeed;
import org.ObserverSubscriber.feeds.Feed;
import org.ObserverSubscriber.feeds.OnScreenFeed;
import org.ObserverSubscriber.feeds.PDFFeed;

public enum FeedFactory {
	INSTANCE;
	
	public Optional<Feed> getFeedObject(FeedType feedType) {
		Feed feed = null;
		switch (feedType) {
			case EMAIL:
				feed = new EmailFeed();
				break;
			case PDF:
				feed = new  PDFFeed();
				break;
			case ON_SCREEN:
				feed = new OnScreenFeed();
				break;
		}
		return Optional.ofNullable(feed);
	}
}
