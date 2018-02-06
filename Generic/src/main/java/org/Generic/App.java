package org.Generic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class App {
	public static <E> void main(String[] args) {

		List<Entity> list = new ArrayList<Entity>();

		Entity entity = new Entity();
		entity.setName("john");
		entity.setId(3);
		entity.setSalary(10.2);

		list.add(entity);

		entity = new Entity();
		entity.setName("james");
		entity.setId(4);
		entity.setSalary(9);

		list.add(entity);

		entity = new Entity();
		entity.setName("caroline");
		entity.setId(1);
		entity.setSalary(8.8);

		list.add(entity);

		entity = new Entity();
		entity.setName("zebrex");
		entity.setId(10);
		entity.setSalary(10.4);

		list.add(entity);

		entity = new Entity();
		entity.setName("poul");
		entity.setId(2);
		entity.setSalary(9.8);

		list.add(entity);

		System.out.println("Based on salary");
		GenericComparator.sortList(list, "salary", SortType.ASC);
		Iterator it = list.iterator();
		while (it.hasNext()) {
			Entity entry = (Entity) it.next();
			System.out.println(entry);
		}

		System.out.println();
		System.out.println("Based on id");
		GenericComparator.sortList(list, "id", SortType.ASC);
		it = list.iterator();
		while (it.hasNext()) {
			Entity entry = (Entity) it.next();
			System.out.println(entry);
		}

		System.out.println();
		System.out.println("Based on salary");
		GenericComparator.sortList(list, "salary", SortType.DESC);
		it = list.iterator();
		while (it.hasNext()) {
			Entity entry = (Entity) it.next();
			System.out.println(entry);
		}
	}
}
