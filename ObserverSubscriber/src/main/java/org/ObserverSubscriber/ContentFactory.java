package org.ObserverSubscriber;

import java.util.Optional;

import org.ObserverSubscriber.contents.AudioContent;
import org.ObserverSubscriber.contents.CaseStudyContent;
import org.ObserverSubscriber.contents.Content;
import org.ObserverSubscriber.contents.MultiMediaCommentContent;
import org.ObserverSubscriber.contents.TextualContent;
import org.ObserverSubscriber.contents.VedioContent;
import org.ObserverSubscriber.contents.WhiteContent;

public enum ContentFactory {
	INSTANCE;
	
	public Optional<Content> getContentObject(ContentType contentType) {
		Content content = null;
		switch (contentType) {
			case TEXTUAL:
				content = new TextualContent();
				break;
			case VEDIO:
				content = new  VedioContent();
				break;
			case AUDIO:
				content = new AudioContent();
				break;
			case WHITE_PAPER:
				content = new WhiteContent();
				break;
			case CASE_STUDY:
				content = new CaseStudyContent();
				break;
			case TEXT_COMMENT:
				content = new TextualContent();
				break;
			case MULTI_MEDIA:
				content = new MultiMediaCommentContent();
				break;
		}
		return Optional.ofNullable(content);
	}
}
