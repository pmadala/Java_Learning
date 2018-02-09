package org.Generic;

/**
 * An enum representing what type of ascending (ASC) or descending (DESC) order
 * @author priyambadam
 */
public enum SortType {
	ASC(1), DESC(-1);

	private int index = 1;

	private SortType(int index) {
		this.index = index;
	}

	public int getValue() {
		return this.index;
	}
	@Override
	public String toString() {
		return String.valueOf(index);
	}
}
