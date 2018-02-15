package org.ObserverSubscriber.contents;

import java.util.Objects;

/**
 * A skeletal representation of the content type
 * @author priyambadam
 *
 */
public abstract class AbstractContent implements Content {
	protected String data;

	/**
	 * set the value of the data in content.
	 * For the first time it will initialize the value of the data to content specific to type
	 */
	public void setData(String data) {
		if (Objects.isNull(this.data)) {
			initializeData();
		}
		this.data = this.data + data;
	}

	public String getData() {
		return data;
	}

	/**
	 * Initialize the data of a content based on the content type it would hold 
	 */
	abstract void initializeData();
}
