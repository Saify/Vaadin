package com.example.containertraining.container;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.example.containertraining.backend.DataProvider;
import com.vaadin.data.Collapsible;
import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItem;

@SuppressWarnings("serial")
public class MyContainer<T> extends AbstractCachedContainer<T> implements
		Container.ItemSetChangeNotifier, Container.Indexed, Container.Sortable,
		Container.Hierarchical, Collapsible {

	private DataProvider<T> dataProvider;
	private BeanItem<T> prototypeBeanItem;
	private int storedSize = -1;

	private final int batchSize = 50;

	public MyContainer(DataProvider<T> dataProvider, T prototypeObject) {
		this.dataProvider = dataProvider;
		this.prototypeBeanItem = new BeanItem<T>(prototypeObject);
		this.dataProvider.hideCollapsedChildren();
	}

	/****************************************/
	/** Methods implemented from Container **/
	/****************************************/
	@Override
	public Item getItem(Object itemId) {
		if (containsId(itemId)) {
			return super.getItem(itemId);
		}
		return null;
	}

	@Override
	public Property<?> getContainerProperty(Object itemId, Object propertyId) {
		Item item = getItem(itemId);
		return item != null ? item.getItemProperty(propertyId) : null;
	}

	@Override
	public boolean containsId(Object itemId) {
		return itemId instanceof Integer && (Integer) itemId >= 0
				&& (Integer) itemId < size();
	}

	@Override
	public Collection<Integer> getItemIds() {
		List<Integer> itemIds = new ArrayList<Integer>();
		for (int i = 0; i < size(); i++) {
			itemIds.add(i);
		}
		return itemIds;
	}

	@Override
	public int size() {
		int newSize = super.size();
		if (storedSize != newSize) {
			storedSize = newSize;
			fireItemSetChange();
		}
		return storedSize;
	}

	@Override
	public Collection<?> getContainerPropertyIds() {
		return prototypeBeanItem.getItemPropertyIds();
	}

	@Override
	public Class<?> getType(Object propertyId) {
		return prototypeBeanItem.getItemProperty(propertyId).getType();
	}

	/**************************************************************/
	/** Methods implemented from Container.ItemSetChangeNotifier **/
	/**************************************************************/

	@Override
	public void addItemSetChangeListener(ItemSetChangeListener listener) {
		super.addItemSetChangeListener(listener);
	}

	@Override
	public void addListener(ItemSetChangeListener listener) {
		super.addItemSetChangeListener(listener);
	}

	@Override
	public void removeItemSetChangeListener(ItemSetChangeListener listener) {
		super.removeItemSetChangeListener(listener);
	}

	@Override
	public void removeListener(ItemSetChangeListener listener) {
		super.removeItemSetChangeListener(listener);
	}

	/************************************************/
	/** Methods implemented from Container.Indexed **/
	/************************************************/
	@Override
	public int indexOfId(Object itemId) {
		return containsId(itemId) ? (Integer) itemId : -1;
	}

	@Override
	public Object getIdByIndex(int index) {
		if (containsId(index)) {
			return new Integer(index);
		}
		throw new IndexOutOfBoundsException();
	}

	@Override
	public List<?> getItemIds(int startIndex, int numberOfItems) {
		if (numberOfItems < 0) {
			throw new IllegalArgumentException();
		}
		if (!containsId(startIndex)) {
			throw new IndexOutOfBoundsException();
		}
		int endIndex = startIndex + numberOfItems - 1;
		if (endIndex > size() - 1) {
			endIndex = size() - 1;
		}
		List<Integer> itemIds = new ArrayList<Integer>();
		for (int i = startIndex; i <= endIndex; i++) {
			itemIds.add(i);
		}
		return itemIds;
	}

	/************************************************/
	/** Methods implemented from Container.Ordered **/
	/************************************************/
	@Override
	public Object nextItemId(Object itemId) {
		return containsId(itemId) && (Integer) itemId < size() - 1 ? (Integer) itemId + 1
				: null;
	}

	@Override
	public Object prevItemId(Object itemId) {
		return containsId(itemId) && (Integer) itemId > 0 ? (Integer) itemId - 1
				: null;
	}

	@Override
	public Object firstItemId() {
		return size() > 0 ? 0 : null;
	}

	@Override
	public Object lastItemId() {
		return size() > 0 ? size() - 1 : null;
	}

	@Override
	public boolean isFirstId(Object itemId) {
		return containsId(itemId) && (Integer) itemId == 0;
	}

	@Override
	public boolean isLastId(Object itemId) {
		return containsId(itemId) && (Integer) itemId == size() - 1;
	}

	/*************************************************/
	/** Methods implemented from Container.Sortable **/
	/*************************************************/

	@Override
	public void sort(Object[] propertyId, boolean[] ascending) {
		dataProvider.sort(propertyId, ascending);
		clearCache();
		fireItemSetChange();
	}

	@Override
	public Collection<?> getSortableContainerPropertyIds() {
		return dataProvider.getSortableFields();
	}

	/****************************************************/
	/** Not to be implemented in a read-only container **/
	/****************************************************/
	@Override
	public Item addItem(Object itemId) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	@Override
	public Object addItem() throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean removeItem(Object itemId)
			throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean addContainerProperty(Object propertyId, Class<?> type,
			Object defaultValue) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean removeContainerProperty(Object propertyId)
			throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean removeAllItems() throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	@Override
	public Object addItemAt(int index) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	@Override
	public Item addItemAt(int index, Object newItemId)
			throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	@Override
	public Object addItemAfter(Object previousItemId)
			throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	@Override
	public Item addItemAfter(Object previousItemId, Object newItemId)
			throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	@Override
	protected Map<Integer, Item> fetchItemBatch(int requestedIndex) {
		int startIndex = (requestedIndex / batchSize) * batchSize;
		List<T> objects = dataProvider.fetchItemBatch(startIndex, batchSize);

		Map<Integer, Item> result = new HashMap<Integer, Item>();
		for (int i = startIndex; i < startIndex + batchSize; i++) {
			result.put(i, new BeanItem<T>(objects.get(i - startIndex)));
		}
		return result;
	}

	@Override
	protected int getCount() {
		return dataProvider.getCount();
	}

	/****************************************************/
	/** Hierarchical **/
	/****************************************************/
	@Override
	public Collection<?> getChildren(Object itemId) {
		return dataProvider.getChildren((Integer) itemId);
	}

	@Override
	public Object getParent(Object itemId) {
		return dataProvider.getParent((Integer) itemId);
	}

	@Override
	public Collection<?> rootItemIds() {
		LinkedList<Integer> roots = new LinkedList<Integer>();
		for (int i = 0; i < size(); i++) {
			if (isRoot(i)) {
				roots.add(i);
			}
		}
		return Collections.unmodifiableCollection(roots);
	}

	@Override
	public boolean areChildrenAllowed(Object itemId) {
		return hasChildren(itemId);
	}

	@Override
	public boolean isRoot(Object itemId) {
		return hasChildren(itemId);
	}

	@Override
	public boolean hasChildren(Object itemId) {
		return dataProvider.hasChildren((Integer) itemId);
	}

	/*******************************************************************/
	/** Hierarchical - Not to be implemented in a read-only container **/
	/*******************************************************************/

	@Override
	public boolean setChildrenAllowed(Object itemId, boolean areChildrenAllowed)
			throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean setParent(Object itemId, Object newParentId)
			throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	/*******************************************************************/
	/** Collapsible **/
	/*******************************************************************/

	@Override
	public void setCollapsed(Object itemId, boolean collapsed) {
		if (!hasChildren(itemId)) {
			return;
		}
		if (isCollapsed(itemId) != collapsed) {
			dataProvider.setCollapsed((Integer) itemId, collapsed);
			clearCache();
			fireItemSetChange();
		}
	}

	@Override
	public boolean isCollapsed(Object itemId) {
		return dataProvider.isCollapsed((Integer) itemId);
	}
}
