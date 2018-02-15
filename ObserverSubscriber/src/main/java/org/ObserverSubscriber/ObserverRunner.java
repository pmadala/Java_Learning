package org.ObserverSubscriber;

import org.ObserverSubscriber.contents.Content;
import org.ObserverSubscriber.feeds.Feed;
import org.ObserverSubscriber.observerPattern.Blog;
import org.ObserverSubscriber.observerPattern.CommentBlog;

/**
 * Implementation for running the program  
 * @author priyambadam
 *
 */

public class ObserverRunner {

	public static void main(String args[]) {

		Feed emailFeed = FeedFactory.INSTANCE.getFeedObject(FeedType.EMAIL).get();
		Feed pdfFeed = FeedFactory.INSTANCE.getFeedObject(FeedType.PDF).get();
		Feed onScreenFeed = FeedFactory.INSTANCE.getFeedObject(FeedType.ON_SCREEN).get();

		Content textContent = ContentFactory.INSTANCE.getContentObject(ContentType.TEXTUAL).get();
		Content textContent1 = ContentFactory.INSTANCE.getContentObject(ContentType.TEXTUAL).get();
		Content vedioContent = ContentFactory.INSTANCE.getContentObject(ContentType.VEDIO).get();
		Content multiMediaContent = ContentFactory.INSTANCE.getContentObject(ContentType.MULTI_MEDIA).get();
		Content whiteContent = ContentFactory.INSTANCE.getContentObject(ContentType.WHITE_PAPER).get();
		Content caseStudyContent = ContentFactory.INSTANCE.getContentObject(ContentType.CASE_STUDY).get();

		textContent.setData("Started");
		Blog textArticle = BlogFactory.INSTANCE.getBlog(BlogType.ARTICLE, textContent).get();
		Blog vedioArticle = BlogFactory.INSTANCE.getBlog(BlogType.ARTICLE, vedioContent).get();
		Blog whitePaper = BlogFactory.INSTANCE.getBlog(BlogType.WHITE_PAPER, whiteContent).get();
		Blog comment = BlogFactory.INSTANCE.getBlog(BlogType.COMMENT, multiMediaContent).get();

		textContent1.setData("Another text article Started");
		Blog textArticle1 = BlogFactory.INSTANCE.getBlog(BlogType.ARTICLE, textContent).get();
		Blog caseStudy = BlogFactory.INSTANCE.getBlog(BlogType.CASE_STUDY, caseStudyContent).get();

		registerObserverAndObservable(textArticle, emailFeed);
		registerObserverAndObservable(textArticle, pdfFeed);
		registerObserverAndObservable(textArticle1, emailFeed);
		registerObserverAndObservable(whitePaper, onScreenFeed);
		registerObserverAndObservable(caseStudy, pdfFeed);
		registerObserverAndObservable(vedioArticle, emailFeed);
		registerObserverAndObservable(comment, onScreenFeed);

		textContent.setData("\nUpdated docs ");
		textArticle.triggerDataChange(textContent);
		System.out.println("#######################################################################################");

		textContent1.setData("\nUpdated docs ");
		textArticle1.triggerDataChange(textContent1);
		System.out.println("#######################################################################################");
		
		whiteContent.setData("\n updating whitePaper");
		whitePaper.triggerDataChange(whiteContent);
		System.out.println("#######################################################################################");
		
		caseStudyContent.setData("\n updating case Study");
		caseStudy.triggerDataChange(caseStudyContent);
		System.out.println("#######################################################################################");
		
		multiMediaContent.setData("\n multi media changed");
		comment.triggerDataChange(multiMediaContent);
		System.out.println("#######################################################################################");
		
		Content textComment = ContentFactory.INSTANCE.getContentObject(ContentType.TEXT_COMMENT).get();
		CommentBlog textCommentBlog = (CommentBlog) BlogFactory.INSTANCE.getBlog(BlogType.COMMENT, textComment).get();
		((CommentBlog) comment).addNestedComment(textCommentBlog);
		System.out.println("#######################################################################################");
	}

	private static <E, T> void registerObserverAndObservable(Blog observable, Feed observer) {
		observable.register(observer);
		observer.addObservable(observable);
	}
}
