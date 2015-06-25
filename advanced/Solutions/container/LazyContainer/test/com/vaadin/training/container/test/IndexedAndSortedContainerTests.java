package com.vaadin.training.container.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import com.example.containertraining.backend.DataProvider;
import com.example.containertraining.container.MyContainer;
import com.example.containertraining.data.Contact;
import com.vaadin.data.util.BeanItem;

public class IndexedAndSortedContainerTests {
	public static final int LARGE_CONTAINER_SIZE = 10000;

	private static MyContainer<Contact> container;
	private static DataProvider<Contact> provider;
	private static BeanItem<Contact> prototype;
	private static int currentSize;

	@BeforeClass
	@SuppressWarnings("unchecked")
	public static void setUp() throws Exception {
		provider = Mockito.mock(DataProvider.class);
		prototype = new BeanItem<Contact>(Mockito.mock(Contact.class));
		currentSize = LARGE_CONTAINER_SIZE;
		recreateContainer();
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void getIdByIndex_minus5_shouldThrowException() {
		container.getIdByIndex(-5);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void getIdByIndex_minus1_shouldThrowException() {
		container.getIdByIndex(-1);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void getIdByIndex_containerSize_shouldThrowException() {
		container.getIdByIndex(currentSize);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void getIdByIndex_containerSizePlus5_shouldThrowException() {
		container.getIdByIndex(currentSize + 5);
	}

	@Test
	public void getIdByIndex_allValidIndexes_validId() {
		for (int i = 0; i < currentSize; i++) {
			assertEquals(i, container.getIdByIndex(i));
		}
	}

	@Test
	public void indexOfId_minus5_minus1() {
		assertEquals(-1, container.indexOfId(-5));
	}

	@Test
	public void indexOfId_minus1_minus1() {
		assertEquals(-1, container.indexOfId(-1));
	}

	@Test
	public void indexOfId_containerSize_minus1() {
		assertEquals(-1, container.indexOfId(currentSize));
	}

	@Test
	public void indexOfId_containerSizePlus5_minus1() {
		assertEquals(-1, container.indexOfId(currentSize + 5));
	}

	@Test
	public void indexOfId_allValidIds_returnsValidIndexes() {
		for (int i = 0; i < currentSize; i++) {
			assertEquals(i, container.indexOfId(i));
		}
	}

	@Test
	public void nextItemId_testAllIndexes_returnsValidId() {
		for (int i = 0; i < currentSize - 1; i++) {
			assertEquals(i + 1, container.nextItemId(i));
		}
	}

	@Test
	public void nextItemId_testMinus1_returnsNull() {
		assertNull(container.nextItemId(-1));
	}

	@Test
	public void nextItemId_testCurrentSize_returnsNull() {
		assertNull(container.nextItemId(currentSize));
	}

	@Test
	public void prevItemId_testAllIndexes_returnsValidId() {
		for (int i = 1; i < currentSize; i++) {
			assertEquals(i - 1, container.prevItemId(i));
		}
	}

	@Test
	public void prevItemId_test0_returnsNull() {
		assertNull(container.prevItemId(0));
	}

	@Test
	public void prevItemId_testTooHighIndex_returnsNull() {
		assertNull(container.prevItemId(currentSize + 2));
	}

	@Test
	public void firstItemId_containerHasItems_returnsCorrectId() {
		assertEquals(0, container.firstItemId());
	}

	@Test
	public void firstItemId_containerHasNoItems_returnsNull() {
		currentSize = 0;
		recreateContainer();
		assertNull(container.firstItemId());
		currentSize = LARGE_CONTAINER_SIZE;
		recreateContainer();
	}

	@Test
	public void lastItemId_containerHasItems_returnsCorrectId() {
		assertEquals(currentSize - 1, container.lastItemId());
	}

	@Test
	public void lastItemId_containerHasNoItems_returnsNull() {
		currentSize = 0;
		recreateContainer();
		assertNull(container.lastItemId());
		currentSize = LARGE_CONTAINER_SIZE;
		recreateContainer();
	}

	@Test
	public void isFirstId_containerHasItemsLastIdGiven_returnsTrue() {
		assertTrue(container.isFirstId(0));
	}

	@Test
	public void isFirstId_containerHasNoItems0Given_returnsFalse() {
		currentSize = 0;
		recreateContainer();
		assertFalse(container.isFirstId(0));
		currentSize = LARGE_CONTAINER_SIZE;
		recreateContainer();
	}

	@Test
	public void isLastId_containerHasItemsLastIdGiven_returnsTrue() {
		assertTrue(container.isLastId(currentSize - 1));
	}

	@Test
	public void isLastId_containerHasNoItems0Give_returnsFalse() {
		currentSize = 0;
		recreateContainer();
		assertFalse(container.isLastId(0));
		currentSize = LARGE_CONTAINER_SIZE;
		recreateContainer();
	}

	@Test
	public void getItemIds_start0count10_correctItemIdsReturned() {
		doTestItemIds(0, 10);
	}

	@Test
	public void getItemIds_start4000count500_correctItemIdsReturned() {
		doTestItemIds(4000, 500);
	}

	@Test
	public void getItemIds_startContainerSizeMinus500count500_correctItemIdsReturned() {
		doTestItemIds(0, 10);
		doTestItemIds(4000, 500);
		doTestItemIds(currentSize - 500, 500);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void getItemIds_startIndexTooLow_shouldThrowException() {
		container.getItemIds(-1, 10);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void getItemIds_startIndexTooHigh_shouldThrowException() {
		container.getItemIds(currentSize, 10);
	}

	@Test(expected = IllegalArgumentException.class)
	public void getItemIds_countIsNegative_shouldThrowException() {
		container.getItemIds(0, -1);
	}

	@Test
	public void sort_twoPropertiesGiven_propertiesReceivedByProvider() {
		Object[] propId = { "foo", "bar" };
		boolean[] asc = { false, true };
		container.sort(propId, asc);
		Mockito.verify(provider).sort(propId, asc);
	}

	@Test
	public void getSortableContainerPropertyIds_initialState_callsProviderMethod() {
		container.getSortableContainerPropertyIds();
		Mockito.verify(provider).getSortableFields();
	}

	private void doTestItemIds(int start, int count)
			throws IndexOutOfBoundsException {
		List<?> itemIds = container.getItemIds(start, count);
		assertTrue("Item ID collection size not correct.",
				itemIds.size() == count);
		Iterator<?> i = itemIds.iterator();
		int counter = start;
		while (i.hasNext()) {
			Object current = i.next();
			assertTrue("Item ID is not correct.", current.equals(counter));
			counter++;
		}
	}

	private static void recreateContainer() {
		Mockito.when(provider.getCount()).thenReturn(currentSize);
		container = new MyContainer<Contact>(provider, prototype.getBean());
		container.size();
	}
}
