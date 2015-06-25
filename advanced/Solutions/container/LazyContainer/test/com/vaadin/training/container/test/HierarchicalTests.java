package com.vaadin.training.container.test;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import com.example.containertraining.backend.DataProvider;
import com.example.containertraining.container.MyContainer;
import com.example.containertraining.data.Contact;
import com.vaadin.data.util.BeanItem;

public class HierarchicalTests {
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
	public void foo() {
	}

	private static void recreateContainer() {
		Mockito.when(provider.getCount()).thenReturn(currentSize);
		container = new MyContainer<Contact>(provider, prototype.getBean());
		container.size();
	}
}
