package org.ObserverSubscriber.contents;

/**
 * A class representing text comment content type
 * @author priyambadam
 *
 */
public class TextCommentContent extends AbstractCommentContent {
	
	@Override
	void initializeData() {
		this.data = "Default Text Comment Content - > ";
	}

}
