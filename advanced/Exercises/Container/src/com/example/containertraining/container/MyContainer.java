package com.example.containertraining.container;

import java.util.Collection;

import com.example.containertraining.backend.DataProvider;
import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.util.AbstractContainer;

public class MyContainer<T> extends AbstractContainer implements
		Container.ItemSetChangeNotifier {

	public MyContainer(DataProvider<T> dataProvider, T prototypeObject) {
		// TODO Implement constructor
	}

	@Override
	public Item getItem(Object itemId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<?> getContainerPropertyIds() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<?> getItemIds() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Property getContainerProperty(Object itemId, Object propertyId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class<?> getType(Object propertyId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean containsId(Object itemId) {
		// TODO Auto-generated method stub
		return false;
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
