package org.ObserverSubscriber.observerPattern;

import java.util.Objects;

public abstract class AbstractContentType implements ContentType{
	protected String data = "";
	
	public void setData(String data) {
		if (Objects.isNull(data)) {
			initializeData();
		} else this.data += data;
	}
	
	public String getData() {	
		return data;
	}
	
	abstract void initializeData(); 
}
