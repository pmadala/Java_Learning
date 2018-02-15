package org.ObserverSubscriber;

import org.ObserverSubscriber.observerPattern.ArticleBlog;
import org.ObserverSubscriber.observerPattern.Blog;
import org.ObserverSubscriber.observerPattern.ContentType;
import org.ObserverSubscriber.observerPattern.EmailFeed;
import org.ObserverSubscriber.observerPattern.Feed;
import org.ObserverSubscriber.observerPattern.TextualContent;

public class ObserverRunner {

	public static void main(String args[]) {
		ContentType textContent = new TextualContent();
		textContent.setData("Started");
		Blog textArticle = new ArticleBlog(textContent);
		Feed feed = new EmailFeed();
		
		textArticle.register(feed);
		feed.setObservable(textArticle);
		
		ContentType updateContent = new TextualContent();
		updateContent.setData("\nUpdated docs ");
		textArticle.triggerDataChange(updateContent);
	}
}
