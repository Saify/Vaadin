package com.example.containertraining.container;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.example.containertraining.backend.DataProvider;
import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.util.AbstractContainer;
import com.vaadin.data.util.BeanItem;

@SuppressWarnings("serial")
public class MyContainer<T> extends AbstractContainer implements
		Container.ItemSetChangeNotifier, Container.Indexed, Container.Sortable {

	private DataProvider<T> dataProvider;
	private BeanItem<T> prototypeBeanItem;
	private int storedSize = -1;

	public MyContainer(DataProvider<T> dataProvider, T prototypeObject) {
		this.dataProvider = dataProvider;
		this.prototypeBeanItem = new BeanItem<T>(prototypeObject);
	}

	/****************************************/
	/** Methods implemented from Container **/
	/****************************************/
	@Override
	public Item getItem(Object itemId) {
		if (containsId(itemId)) {
			return new BeanItem<T>(dataProvider.fetchItemBatch(
					indexOfId(itemId), 1).get(0));
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
		int newSize = dataProvider.getCount();
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
		if (startIndex < 0 || startIndex > size() - 1) {
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
}
