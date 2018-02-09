package org.Generic;

/**
 * Enum type for String objects 
 * @author priyambadam
 *
 */
public enum StringEnum {
	ONE("one"),
	TWO("two"),
	THREE("three"),
	FOUR("four"),
	FIVE("five");
	
	private String value;

	private StringEnum(String value) {
	      this.value = value;
	   }

	public String getValue() {
		return value;
	}
	@Override
	public String toString() {
		return value;
	}
}
