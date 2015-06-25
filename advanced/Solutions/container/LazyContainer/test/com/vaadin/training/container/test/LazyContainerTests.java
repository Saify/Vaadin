package com.vaadin.training.container.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import com.example.containertraining.backend.DataProvider;
import com.example.containertraining.container.MyContainer;
import com.example.containertraining.data.Contact;
import com.vaadin.data.util.BeanItem;

public class LazyContainerTests {
    public static final int LARGE_CONTAINER_SIZE = 10000;

    private static MyContainer<Contact> container;
    private static DataProvider<Contact> provider;
    private static BeanItem<Contact> prototype;
    private static int currentSize;

    @BeforeClass
    public static void setUp() throws Exception {
        prototype = new BeanItem<Contact>(Mockito.mock(Contact.class));
        currentSize = LARGE_CONTAINER_SIZE;
        recreateContainer();
    }

    @Test
    public void size_providerHasItems_shouldCallProviderOnceAndProvideCorrectValues() {
        recreateContainer();
        int size = container.size();
        int size2 = container.size();
        Mockito.verify(provider).getCount();
        assertEquals(LARGE_CONTAINER_SIZE, size);
        assertEquals(LARGE_CONTAINER_SIZE, size2);
    }

    @Test
    public void getItem_withId0_asksForCorrect50ItemsFromProvider() {
        recreateContainer();
        List<Contact> contactSubset = new ArrayList<Contact>();
        for (int i = 0; i < 50; i++) {
            contactSubset.add(new Contact(i));
        }
        Mockito.when(provider.fetchItemBatch(0, 50)).thenReturn(contactSubset);
        container.getItem(0);
        Mockito.verify(provider).fetchItemBatch(0, 50);
        Mockito.verify(provider).fetchItemBatch(Mockito.anyInt(),
                Mockito.anyInt());
    }

    @Test
    public void getItem_withId8354_asksForCorrect50ItemsFromProvider() {
        recreateContainer();
        List<Contact> contactSubset = new ArrayList<Contact>();
        for (int i = 8350; i < 8400; i++) {
            contactSubset.add(new Contact(i));
        }
        Mockito.when(provider.fetchItemBatch(8350, 50)).thenReturn(contactSubset);
        container.getItem(8354);
        Mockito.verify(provider).fetchItemBatch(8350, 50);
        Mockito.verify(provider).fetchItemBatch(Mockito.anyInt(),
                Mockito.anyInt());
    }

    @SuppressWarnings("unchecked")
    private static void recreateContainer() {
        provider = Mockito.mock(DataProvider.class);
        Mockito.when(provider.getCount()).thenReturn(currentSize);
        container = new MyContainer<Contact>(provider, prototype.getBean());
        container.size();
    }
}
