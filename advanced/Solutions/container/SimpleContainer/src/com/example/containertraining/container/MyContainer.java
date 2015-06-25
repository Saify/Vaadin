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
		Container.ItemSetChangeNotifier {

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
					(Integer) itemId, 1).get(0));
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
}
