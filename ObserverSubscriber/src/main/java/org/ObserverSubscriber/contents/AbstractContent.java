package org.ObserverSubscriber.contents;

import java.util.Objects;

public abstract class AbstractContent implements Content {
	protected String data;

	public void setData(String data) {
		if (Objects.isNull(this.data)) {
			initializeData();
		}
		this.data = this.data + data;
	}

	public String getData() {
		return data;
	}

	abstract void initializeData();
}
