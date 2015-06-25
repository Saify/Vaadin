package com.vaadin.training.container.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.example.containertraining.backend.DataProvider;
import com.example.containertraining.container.MyContainer;
import com.example.containertraining.data.Contact;
import com.vaadin.data.Container.ItemSetChangeEvent;
import com.vaadin.data.Container.ItemSetChangeListener;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItem;

public class ReadOnlyContainerTests {
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

    @Test
    public void size_providerHasItems_correctSizeReturned() {
        assertTrue("Size was not " + currentSize
                + " when DataProvider count was " + currentSize + ".",
                container.size() == currentSize);
    }
    
    @Test
    public void size_theSizeHasChanged_itemSetChangeIsFired() {
    	ItemSetChangeListener itemSetChangeListener = Mockito.mock(ItemSetChangeListener.class);
    	
    	// Create new container to make sure the size is changed even though cache would be used
        container = new MyContainer<Contact>(provider, prototype.getBean());
        container.addItemSetChangeListener(itemSetChangeListener);
        container.size();
    	
    	verify(itemSetChangeListener).containerItemSetChange(Mockito.any(ItemSetChangeEvent.class));
    }

    @Test
    public void getContainerPropertyIds_providerHasItems_correctIdsReturned() {
        Collection<?> propIds = container.getContainerPropertyIds();
        Collection<?> correctPropIds = prototype.getItemPropertyIds();
        for (Object id : propIds) {
            assertTrue("Property ID missing: " + id.toString(),
                    correctPropIds.contains(id));
        }
        assertTrue("Propert ID collection size is not correct.",
                propIds.size() == correctPropIds.size());
    }

    @Test
    public void getContainerPropertyIds_providerHasItems_propertyTypesMatch() {
        Collection<?> propIds = container.getContainerPropertyIds();
        Collection<?> correctPropIds = prototype.getItemPropertyIds();
        for (Object id : propIds) {
            assertTrue("Property type mismatch: " + id.toString(), container
                    .getType(id)
                    .equals(prototype.getItemProperty(id).getType()));
        }
        assertTrue("Property ID collection size is not correct.",
                propIds.size() == correctPropIds.size());
    }

    @Test
    public void getItemIds_providerHasItemsAndHasNoItems_correctItemIdsAreReturned() {
        doTestItemIds();
        currentSize = 0;
        recreateContainer();
        doTestItemIds();
        currentSize = LARGE_CONTAINER_SIZE;
        recreateContainer();
    }

    @Test
    public void getItem_providerHasItems_correctItemIsReturned() {
        stubDataProviderToReturnContactsWithId();
        for (int i = 0; i < currentSize; i++) {
            Item item = container.getItem(i);
            assertNotNull("An item was not returned at all.", item);
            assertTrue("Correct item was not returned.",
                    item.getItemProperty("id").getValue().equals(i));
        }
    }


    @Test
    public void getContainerProperty_providerHasItems_correctPropertiesIsReturned() {
    	stubDataProviderToReturnContactsWithId();
    	for (int i = 0; i < currentSize; i++) {
    		Property<?> property = container.getContainerProperty(i, "id");
    		assertNotNull("An property was not returned at all.", property);
    		assertTrue("Correct item was not returned.",
    				property.getValue().equals(i));
    	}
    }

    @Test
    public void containsId_calledWithValidAndInvalidValues_shouldReturnCorrectAnswer() {
        assertFalse("Should not contain ID -5", container.containsId(-5));
        assertFalse("Should not contain ID -1", container.containsId(-1));
        assertFalse("Should not contain ID " + currentSize,
                container.containsId(currentSize));
        assertFalse("Should not contain ID " + (currentSize + 5),
                container.containsId(currentSize + 5));
        for (int i = 0; i < currentSize; i++) {
            assertTrue("Could not find ID: " + i, container.containsId(i));
        }
    }

    @Test
    public void getItem_providerHasItems_correctItemWithCorrectPropertiesReturned() {
        int targetIndex = 7000;
        int batchSize = 50;
        List<Contact> contacts = new ArrayList<Contact>();
        List<Contact> contactSubset = new ArrayList<Contact>();
        for (int i = 0; i < currentSize; i++) {
            if (i != targetIndex) {
                contacts.add(new Contact(i));
            } else {
                Contact c = new Contact(targetIndex);
                c.setStreet("testing avenue");
                contacts.add(c);
                contactSubset.add(c);
            }
            if (i > targetIndex && i <= targetIndex + batchSize) {
                contactSubset.add(new Contact(i));
            }
        }
        Mockito.when(provider.fetchItemBatch(7000, 1)).thenReturn(
                contactSubset.subList(0, 1));
        Mockito.when(provider.fetchItemBatch(7000, 50)).thenReturn(
                contactSubset);
        Item item = container.getItem(targetIndex);
        assertNotNull("An item was not returned at all.", item);
        assertTrue("Correct property value was not returned.", item
                .getItemProperty("street").getValue().equals("testing avenue"));
    }

    private void doTestItemIds() {
        Collection<?> itemIds = container.getItemIds();
        assertTrue("Item ID collection size not correct.",
                itemIds.size() == currentSize);
        Iterator<?> i = itemIds.iterator();
        int counter = 0;
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
    
	private void stubDataProviderToReturnContactsWithId() {
		Mockito.when(
                provider.fetchItemBatch(Mockito.anyInt(), Mockito.anyInt()))
                .thenAnswer(new Answer() {

                    @Override
                    public Object answer(InvocationOnMock invocation)
                            throws Throwable {
                        Object[] args = invocation.getArguments();
                        List<Contact> contacts = new ArrayList<Contact>();
                        for (int i = (Integer) args[0]; i < currentSize
                                && i < (Integer) args[0] + (Integer) args[1]; i++) {
                            contacts.add(new Contact(i));
                        }
                        return contacts;
                    }
                });
	}
    
}
