package org.ObserverSubscriber;

import java.util.Optional;

import org.ObserverSubscriber.contents.Content;
import org.ObserverSubscriber.observerPattern.ArticleBlog;
import org.ObserverSubscriber.observerPattern.BlogItem;
import org.ObserverSubscriber.observerPattern.CaseStudyBlog;
import org.ObserverSubscriber.observerPattern.CommentBlog;
import org.ObserverSubscriber.observerPattern.WhitePaperBlog;

/**
 * A singleton factory class returning instance of blog items based on the categories 
 * 
 * @author priyambadam
 *
 */
public enum BlogItemFactory {
	INSTANCE;

	public Optional<BlogItem> getBlog(BlogItemType blogType, Content content) {
		BlogItem blog = null;
		switch (blogType) {
			case ARTICLE:
				blog = new ArticleBlog(content);
				break;
			case WHITE_PAPER:
				blog = new  WhitePaperBlog(content);
				break;
			case CASE_STUDY:
				blog = new CaseStudyBlog(content);
				break;
			case COMMENT:
				blog = new CommentBlog(content);
				break;
		}
		return Optional.ofNullable(blog);
	}
}
