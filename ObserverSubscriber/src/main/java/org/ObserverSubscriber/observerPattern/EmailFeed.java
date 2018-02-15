package org.ObserverSubscriber.observerPattern;

public class EmailFeed extends AbstractFeed{

	@Override
	public void setObservable(Blog blog) {
		this.blog = blog;
	}

	@Override
	public void update() {
		System.out.println("Email Feed updating ->" +  blog.getContent().getData());
	}

}
