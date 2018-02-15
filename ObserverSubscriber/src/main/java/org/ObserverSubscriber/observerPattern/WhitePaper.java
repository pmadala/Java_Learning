package org.ObserverSubscriber.observerPattern;

public class WhitePaper extends AbstractBlog {

	public <T> WhitePaper(T content) {
		super(content);
	}
	
	@Override
	public void register(Feed observer) {
		
	}

	@Override
	public void unregister(Feed observer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void triggerDataChange(ContentType content) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyObservers() {
		// TODO Auto-generated method stub
		
	}
	

}
