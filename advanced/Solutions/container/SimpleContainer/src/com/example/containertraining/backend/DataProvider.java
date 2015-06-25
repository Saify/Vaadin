package com.example.containertraining.backend;

import java.util.Collection;
import java.util.List;

import com.vaadin.data.Item;

public interface DataProvider<T> {

	/**
	 * Gets the total number of existing data objects
	 * 
	 * @return total number of data objects
	 */
	public int getCount();

	/**
	 * Fetches a batch of data objects
	 * 
	 * @param startIndex
	 *            the index of the first data object to add to the batch
	 * @param count
	 *            the number of items to fetch to the batch
	 * @return The batch of items as a List
	 */
	public List<T> fetchItemBatch(int startIndex, int count);

	/**
	 * Gets the field names of the class <code>T</code> which can be used to
	 * sort the items.
	 * 
	 * Corresponds to
	 * {@link com.vaadin.data.Container.Sortable#getSortableContainerPropertyIds()
	 * Container.Sortable.getSortableContainerPropertyIds()} method.
	 * 
	 * @return the names of fields that can be used for sorting objects
	 */
	public List<Object> getSortableFields();

	/**
	 * Sort method.
	 * 
	 * Sorts the data objects in data provider.
	 * 
	 * Corresponds to
	 * {@link com.vaadin.data.Container.Sortable#sort(Object[] propertyId, boolean[] ascending)
	 * Container.Sortable.sort()} method.
	 * 
	 * @param fieldNames
	 *            Array of field names of class <code>T</code>, whose values are
	 *            used to sort the objects in data provider as primary,
	 *            secondary, ... sorting criterion. All of the field names must
	 *            be in the collection returned by {@link #getSortableFields()}
	 * @param ascending
	 *            Array of sorting order flags corresponding to each field used
	 *            in sorting. Use <code>true</code> to sort in ascending order,
	 *            <code>false</code> to use descending order.
	 */
	public void sort(Object[] fieldNames, boolean[] ascending);

	/**
	 * Gets the indexes of all Items that are children of the specified index.
	 * The returned collection is unmodifiable.
	 * 
	 * Corresponds to
	 * {@link com.vaadin.data.Container.Hierarchical#getChildren(Object itemId)
	 * Container.Hierarchical.getChildren()} method.
	 * 
	 * @param index
	 *            the index of the Item whose children the caller is interested
	 *            in
	 * @return An unmodifiable {@link java.util.Collection collection}
	 *         containing the indexes of all other Items that are children in
	 *         the container hierarchy
	 */
	public Collection<?> getChildren(int index);

	/**
	 * Gets the index of the parent Item of the specified Item.
	 * 
	 * Corresponds to
	 * {@link com.vaadin.data.Container.Hierarchical#getParent(Object itemId)
	 * Container.Hierarchical.getParent()} method.
	 * 
	 * @param index
	 *            index of the Item whose parent the caller wishes to find out.
	 * @return the index of the parent Item. Will be <code>null</code> if the
	 *         specified Item is a root element.
	 */
	public Integer getParent(int index);

	/**
	 * <p>
	 * Tests if the Item specified with <code>index</code> has child Items or if
	 * it is a leaf. The {@link #getChildren(int index)} method always returns
	 * <code>null</code> for leaf Items.
	 * </p>
	 * 
	 * Corresponds to
	 * {@link com.vaadin.data.Container.Hierarchical#hasChildren(Object itemId)
	 * Container.Hierarchical.hasChildren()} method.
	 * 
	 * @param index
	 *            index of the Item to be tested
	 * @return <code>true</code> if the specified Item has children,
	 *         <code>false</code> if not (is a leaf)
	 */
	public boolean hasChildren(int index);

	/**
	 * After this call, the children of collapsed items are not taken into
	 * account to the size() and fetchBatch() methods.
	 */
	public void hideCollapsedChildren();

	/**
	 * <p>
	 * Collapsing the {@link Item} indicated by <code>index</code> hides all
	 * children, and their respective children
	 * </p>
	 * 
	 * <p>
	 * If called on a leaf, this method does nothing.
	 * </p>
	 * 
	 * Corresponds to
	 * {@link com.vaadin.data.Collapsible#setCollapsed(Object itemId, boolean collapsed)
	 * Collapsible.setCollapsed()} method.
	 * 
	 * @param index
	 *            the index of the collapsed {@link Item}
	 * @param collapsed
	 *            <code>true</code> if you want to collapse the children below
	 *            this item. <code>false</code> if you want to uncollapse the
	 *            children.
	 */
	public void setCollapsed(int index, boolean collapsed);

	/**
	 * <p>
	 * Checks whether the item, identified by <code>index</code> is collapsed or
	 * not.
	 * </p>
	 * 
	 * <p>
	 * If an item is "collapsed" its children are not included in methods used
	 * to list Items in this data provider.
	 * </p>
	 * 
	 * Corresponds to
	 * {@link com.vaadin.data.Collapsible#isCollapsed(Object itemId)
	 * Collapsible.isCollapsed()} method.
	 * 
	 * @param index
	 *            The index of the item that is to be checked.
	 * @return <code>true</code> if the item identified by <code>index</code> is
	 *         currently collapsed, otherwise <code>false</code>.
	 */
	public boolean isCollapsed(int index);

}
