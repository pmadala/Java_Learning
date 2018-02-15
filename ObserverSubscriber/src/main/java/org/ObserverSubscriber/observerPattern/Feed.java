package org.ObserverSubscriber.observerPattern;

public interface Feed {
	
	public void setObservable(Blog blog);
	
	public void update();
}
