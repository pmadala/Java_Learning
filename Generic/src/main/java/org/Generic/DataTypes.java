package org.Generic;

public enum DataTypes {
	INTVALUE(1),
	DOUBLEVALUE(1.0),
	FLOATVALUE(1.0f),
	STRINGVALUE("GHKH");
	
	private Object value;

	private DataTypes(Object value) {
	      this.value = value;
	   }

	public Object getValue() {
		return value;
	}
	@Override
	public String toString() {
		return String.valueOf(value);
	}
}
