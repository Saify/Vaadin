package com.example.containertraining.backend;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.example.containertraining.data.Contact;

public class DataProviderImpl implements DataProvider<Contact> {

	private static final int CONTAINER_SIZE = 10000;

	// from parent to children
	private Map<Contact, LinkedList<Contact>> relations;
	// from child to parent
	private Map<Contact, Contact> parents;
	// from Contact to current index
	private Map<Contact, Integer> indexes;
	// from Contact to current index
	private Map<Contact, Boolean> expandedItems;

	private List<Object> sortables;
	private List<Contact> currentItems;
	private List<Contact> roots;
	private List<Contact> currentRoots;
	private DefaultItemSorter sorter;

	private boolean hideCollapsedChildren;

	public DataProviderImpl() {
		relations = new HashMap<Contact, LinkedList<Contact>>();
		parents = new HashMap<Contact, Contact>();
		indexes = new HashMap<Contact, Integer>();
		expandedItems = new HashMap<Contact, Boolean>();
		roots = new ArrayList<Contact>();
		currentRoots = new ArrayList<Contact>();
		currentItems = new ArrayList<Contact>();

		int id = 0;
		for (int i = 0; i < CONTAINER_SIZE / 10; i++) {
			Contact root = new Contact(id++, "Firstname", "Lastname", "Street "
					+ i, "City", "555-3407" + i, "Team Leader");
			roots.add(root);
			LinkedList<Contact> children = new LinkedList<Contact>();
			for (int j = 0; j < 10; ++j) {
				Contact child = new Contact(id++, "Firstname", "Lastname",
						"Street " + i + j, "City", "555-3407" + i + j,
						"Team Member");
				children.add(child);
				parents.put(child, root);
			}
			relations.put(root, children);
		}
		sortables = new ArrayList<Object>();
		sortables.add("firstName");
		sortables.add("lastName");
		sortables.add("street");
		sortables.add("phone");
		sortables.add("city");
		sortables.add("title");

		currentRoots = roots;
		updateCurrentItems();

		sorter = new DefaultItemSorter();
	}

	private void updateCurrentItems() {
		currentItems.clear();
		for (Contact root : currentRoots) {
			currentItems.add(root);
			if (!isCollapsed(root) || !hideCollapsedChildren) {
				currentItems.addAll(relations.get(root));
			}
		}
		updateIndexes();
	}

	private void updateIndexes() {
		indexes.clear();
		for (int i = 0; i < currentItems.size(); ++i) {
			indexes.put(currentItems.get(i), i);
		}
	}

	@Override
	public int getCount() {
		debug("Returning size: " + currentItems.size());
		return currentItems.size();
	}

	@Override
	public List<Contact> fetchItemBatch(int startIndex, int count) {
		List<Contact> batch = new ArrayList<Contact>();
		for (int i = startIndex; i < startIndex + count; i++) {
			batch.add(currentItems.get(i));
		}
		debug("Returning " + batch.size() + " items with indexes: "
				+ startIndex + " - " + (startIndex + count - 1) + "");
		return Collections.unmodifiableList(batch);
	}

	public void sort(Object[] fieldNames, boolean[] ascending) {
		if (fieldNames == null || fieldNames.length == 0 || ascending == null
				|| ascending.length == 0
				|| fieldNames.length != ascending.length) {
			currentRoots = roots;
			updateCurrentItems();
			debug("Clearing the sort...");
		} else {
			String criteria = "";
			for (int i = 0; i < fieldNames.length; i++) {
				if (i > 0)
					criteria += ", ";
				criteria += fieldNames[i];
				criteria += ascending[i] ? "[asc]" : "[desc]";
			}
			debug("Sorting data based on: " + criteria);
			String[] names = new String[fieldNames.length];
			for (int i = 0; i < fieldNames.length; i++)
				names[i] = (String) fieldNames[i];
			sorter.setSortProperties(names, ascending);
			Collections.sort(currentRoots, sorter);
			updateCurrentItems();
		}
	}

	public List<Object> getSortableFields() {
		return sortables;
	}

	private void debug(String msg) {
		System.err.println("DataProvider -> " + msg);
	}

	private String getFieldValue(Contact target, String fieldName) {
		if ("firstName".equals(fieldName))
			return target.getFirstName();
		if ("lastName".equals(fieldName))
			return target.getLastName();
		if ("street".equals(fieldName))
			return target.getStreet();
		if ("city".equals(fieldName))
			return target.getCity();
		if ("title".equals(fieldName))
			return target.getTitle();
		if ("phone".equals(fieldName))
			return target.getPhone();
		return null;
	}

	@SuppressWarnings("serial")
	private class DefaultItemSorter implements Comparator<Object>, Cloneable,
			Serializable {

		private String[] sortPropertyIds;
		private boolean[] sortDirections;
		private Comparator<Object> propertyValueComparator = new DefaultValueComparator();

		@Override
		public int compare(Object o1, Object o2) {
			Contact item1 = (Contact) o1;
			Contact item2 = (Contact) o2;

			if (item1 == null) {
				if (item2 == null) {
					return 0;
				} else {
					return 1;
				}
			} else if (item2 == null) {
				return -1;
			}

			for (int i = 0; i < sortPropertyIds.length; i++) {

				int result = compareProperty(sortPropertyIds[i],
						sortDirections[i], item1, item2);

				if (result != 0) {
					return result;
				}

			}

			return 0;
		}

		protected int compareProperty(String fieldName, boolean sortDirection,
				Contact item1, Contact item2) {
			final Object value1 = getFieldValue(item1, fieldName);
			final Object value2 = getFieldValue(item2, fieldName);
			int r = 0;
			if (sortDirection) {
				r = propertyValueComparator.compare(value1, value2);
			} else {
				r = propertyValueComparator.compare(value2, value1);
			}
			return r;
		}

		public void setSortProperties(String[] fieldName, boolean[] ascending) {
			final List<String> ids = new ArrayList<String>();
			final List<Boolean> orders = new ArrayList<Boolean>();
			final Collection<?> sortable = getSortableFields();
			for (int i = 0; i < fieldName.length; i++) {
				if (sortable.contains(fieldName[i])) {
					ids.add(fieldName[i]);
					orders.add(Boolean
							.valueOf(i < ascending.length ? ascending[i] : true));
				}
			}
			sortPropertyIds = ids.toArray(new String[ids.size()]);
			sortDirections = new boolean[orders.size()];
			for (int i = 0; i < sortDirections.length; i++) {
				sortDirections[i] = (orders.get(i)).booleanValue();
			}
		}
	}

	@SuppressWarnings("serial")
	private class DefaultValueComparator implements Comparator<Object>,
			Serializable {
		@Override
		@SuppressWarnings("unchecked")
		public int compare(Object o1, Object o2) {
			int r = 0;
			if (o1 != null && o2 != null) {
				r = ((Comparable<Object>) o1).compareTo(o2);
			} else if (o1 == o2) {
				r = 0;
			} else {
				if (o1 == null) {
					r = -1;
				} else {
					r = 1;
				}
			}
			return r;
		}
	}

	@Override
	public Collection<?> getChildren(int index) {
		if (!hasChildren(index)) {
			return null;
		}
		Contact contact = currentItems.get(index);
		LinkedList<Integer> childrenIndexes = new LinkedList<Integer>();
		for (Contact child : relations.get(contact)) {
			childrenIndexes.add(indexes.get(child));
		}
		return childrenIndexes;
	}

	@Override
	public Integer getParent(int index) {
		Contact contact = currentItems.get(index);
		Contact parent = parents.get(contact);
		return indexes.get(parent);
	}

	@Override
	public boolean hasChildren(int index) {
		Contact contact = currentItems.get(index);
		return relations.containsKey(contact);
	}

	@Override
	public void setCollapsed(int index, boolean collapsed) {
		Contact contact = currentItems.get(index);
		this.expandedItems.put(contact, !collapsed);
		updateCurrentItems();
	}

	@Override
	public boolean isCollapsed(int index) {
		Contact contact = currentItems.get(index);
		return isCollapsed(contact);
	}

	@Override
	public void hideCollapsedChildren() {
		this.hideCollapsedChildren = true;
		updateCurrentItems();
	}

	private boolean isCollapsed(Contact contact) {
		Boolean expanded = expandedItems.get(contact);
		return (expanded == null || !expanded);
	}

}
