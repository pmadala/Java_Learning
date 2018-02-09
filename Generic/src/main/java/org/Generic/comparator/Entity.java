package org.Generic.comparator;

import java.util.ArrayList;
import java.util.List;

/**
 * A java entity POJO for each roe in the table along with all the column values
 * 
 * @author priyambadam
 *
 */
public class Entity {
	private String name;
	private int id;
	private double salary;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		String returnVal = "Name : " + getName() + " id : " + getId() + " salary : " + getSalary();
		return returnVal;
	}
}
