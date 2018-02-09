package org.Generic.comparator;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.Generic.SortType;


/**
 * Generic comparator implementation
 * 
 * @author priyambadam
 *
 * @param <T>
 */
public class GenericComparator<T extends Entity> implements Comparator<T> {

	private SortType sortType = null;
	private String methodName = null;

	/**
	 * Construct a GenericComparator according to field and type
	 * 
	 * @param sortField - field for ordering
	 * @param sortType - ascending (ASC) or descending (DESC)
	 */
	public GenericComparator(String sortField, SortType sortType) {
		this.sortType = sortType;
		this.methodName = ReflectionUtil.buildGetMethodName(sortField);
	}

	@SuppressWarnings("unchecked")
	public int compare(T obj1, T obj2) {
		try {
			Method method1 = obj1.getClass().getMethod(this.methodName, new Class[] {});
			Comparable comp1 = (Comparable) method1.invoke(obj1, new Object[] {});

			Method method2 = obj1.getClass().getMethod(this.methodName, new Class[] {});
			Comparable comp2 = (Comparable) method2.invoke(obj2, new Object[] {});

			return comp1.compareTo(comp2) * this.sortType.getValue();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}

	/**
	 * Organizes the List<T> according to the field and the type (ASC or DESC)
	 * 
	 * @param <T> - class of objects to be sorted
	 * @param list - List<T> to be ordered
	 * @param sortField - field for ordering
	 * @param sortType - type of ordering (ASC or DESC)
	 */

	public static <T extends Entity> void sortList(List<T> list, String sortField, SortType sortType) {
		GenericComparator<T> comparator = new GenericComparator<T>(sortField, sortType);
		Collections.sort(list, comparator);
	}

}
