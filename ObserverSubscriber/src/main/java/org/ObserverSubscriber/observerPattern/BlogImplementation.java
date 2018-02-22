package org.ObserverSubscriber.observerPattern;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.ObserverSubscriber.BlogItemType;
import org.ObserverSubscriber.feeds.Feed;

/**
 * A class representing the implementation for blog object 
 * Singleton pattern implemented since at run time there would be only blog object so far in requirement
 * @author priyambadam
 *
 * @param <E>
 * @param <T>
 */
public class BlogImplementation extends AbstractBlog{

	private static Blog INSTANCE;
	
	protected Map<EventType, List<Feed>> eventVsObserverMap = new HashMap<>();
	protected Map<BlogItemType, List<Feed>> itemVsObserverMap = new HashMap<>();
	protected Set<BlogItem> observableBlogItems = new HashSet();

	public static Blog getInstance() {
		if (INSTANCE == null ) {
			synchronized (BlogImplementation.class) {
				if(INSTANCE == null) {
					INSTANCE = new BlogImplementation();
				}
			}
		}
		return INSTANCE;
	}
	
	@Override
	public void register(Feed observer, BlogItemType blogItemType, EventType eventType) {
		if (!itemVsObserverMap.containsKey(blogItemType)) {
			List<Feed> observers = new ArrayList();
			observers.add(observer);
			itemVsObserverMap.put(blogItemType,observers);
		} else {
			List<Feed> observers = itemVsObserverMap.get(blogItemType);
			observers.add(observer);
			itemVsObserverMap.put(blogItemType,observers);
		}
		
		if (!eventVsObserverMap.containsKey(eventType)) {
			List<Feed> observers = new ArrayList();
			observers.add(observer);
			eventVsObserverMap.put(eventType, observers);
		} else {
			List<Feed> observers = eventVsObserverMap.get(eventType);
			observers.add(observer);
			eventVsObserverMap.put(eventType,observers);
		}
		
	}


	@Override
	public void unregister(Feed observer, BlogItemType blogItemType, EventType eventType) {
		if (itemVsObserverMap.containsKey(blogItemType) && eventVsObserverMap.containsKey(eventType)) {
			itemVsObserverMap.remove(blogItemType);
			eventVsObserverMap.remove(eventType);
		}
	}

	@Override
	public void triggerDataChange(BlogItemType blogItemType, EventType eventType) {
		
		if (itemVsObserverMap.containsKey(blogItemType) && eventVsObserverMap.containsKey(eventType)) {
			List<Feed> itemObserver = itemVsObserverMap.get(blogItemType);
			List<Feed> eventObserver = eventVsObserverMap.get(eventType);
			List<Feed> notifiableObserver = itemObserver.stream().filter(o-> eventObserver.contains(o)).collect(Collectors.toList());
			notifyObservers(notifiableObserver, blogItemType, eventType);
		}
	}


	@Override
	public void notifyObservers(List notifiableObserver, BlogItemType blogItemType, EventType eventType) {
		notifiableObserver.forEach(s-> ((Feed) s).updateForBlog(blogItemType, eventType));	
		
	}
	
	public void addObserverableBlogItem( BlogItem blogItem) {
		observableBlogItems.add(blogItem);
	}
	
	

}
