package com.vaadin.training.fundamentals.exercises.ex8;

import java.util.Calendar;
import java.util.Date;

import com.vaadin.data.Container;
import com.vaadin.data.Container.Filter;
import com.vaadin.data.Item;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class ContainerFilters extends CustomComponent implements View {

	private Container.Filterable container;

	public ContainerFilters() {
		VerticalLayout layout = new VerticalLayout();
		container = ContainerHelper.createProductContainer();
		
		// TODO create layout for DateFields
		// TODO create and populate Table

		setCompositionRoot(layout);
	}

	/**
	 * Custom DateRangeFilter that checks that the given field is between two
	 * given dates
	 * 
	 */
	private static class DateRangeFilter implements Filter {

		public DateRangeFilter(Date startDate, Date endDate) {
			// TODO
		}

		@Override
		public boolean passesFilter(Object itemId, Item item)
				throws UnsupportedOperationException {
			return false;
		}

		@Override
		public boolean appliesToProperty(Object propertyId) {
			return false;
		}

	}

	/**
	 * Helper method which you can use to clear date objects in order to make
	 * sure that times are not taken into account when filtering.
	 * 
	 * @param date
	 * @return
	 */
	private Date getCleanDate(Date date) {

		if (date == null) {
			return null;
		}

		// Ticket #6081
		final Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.MILLISECOND, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.HOUR, 0);
		return cal.getTime();
	}

	@Override
	public void enter(ViewChangeEvent event) {

	}
}
