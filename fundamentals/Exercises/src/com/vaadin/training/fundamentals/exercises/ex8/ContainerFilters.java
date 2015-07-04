package com.vaadin.training.fundamentals.exercises.ex8;

import java.util.Calendar;
import java.util.Date;

import com.vaadin.data.Container;
import com.vaadin.data.Container.Filter;
import com.vaadin.data.Item;
import com.vaadin.data.util.filter.And;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.DateField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class ContainerFilters extends CustomComponent implements View {

	private Container.Filterable container;
	private DateField start;
	private DateField end;
	private Label totalRecords;

	public ContainerFilters() {
		VerticalLayout layout = new VerticalLayout();
		container = ContainerHelper.createProductContainer();

		// TODO create layout for DateFields
		layout.addComponent(createdDateFieldLayout());

		// TODO create and populate Table
		Table productDataTable = new Table();
		productDataTable.setContainerDataSource(container);
		 totalRecords.setValue("Total Products: " +
		 productDataTable.size());
		layout.addComponent(productDataTable);
		setCompositionRoot(layout);
	}

	private Layout createdDateFieldLayout() {
		HorizontalLayout dateFieldsLayout = new HorizontalLayout();
		Button filterData = new Button("Filter");
		start = new DateField("Start");
		end = new PopupDateField("End");
		totalRecords = new Label();
		dateFieldsLayout.setSpacing(true);
		dateFieldsLayout.addComponents(start, end, filterData, totalRecords);
		dateFieldsLayout.setComponentAlignment(filterData,
				Alignment.BOTTOM_LEFT);
		dateFieldsLayout.setComponentAlignment(totalRecords,
				Alignment.BOTTOM_LEFT);

		filterData.addClickListener(new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				// Filter Data
				container.removeAllContainerFilters();
				container.addContainerFilter(new DateRangeFilter(start
						.getValue(), end.getValue()));
				 totalRecords.setValue("Total Products: " +
						 container.size());
			}
		});

		return dateFieldsLayout;
	}

	/**
	 * Custom DateRangeFilter that checks that the given field is between two
	 * given dates
	 * 
	 */
	private static class DateRangeFilter implements Filter {

		private Filter myDateFilter;

		public DateRangeFilter(Date startDate, Date endDate) {
			if (startDate == null && endDate == null) {
				myDateFilter = new Compare.Greater("available", new Date(0L));
			} else if (startDate != null && endDate == null) {
				myDateFilter = new Compare.GreaterOrEqual("available",
						startDate);
			} else if (startDate == null && endDate != null) {
				myDateFilter = new Compare.LessOrEqual("available", endDate);
			} else {
				myDateFilter = new And(new Compare.GreaterOrEqual("available",
						startDate), new Compare.LessOrEqual("available",
						endDate));
			}
		}

		@Override
		public boolean passesFilter(Object itemId, Item item)
				throws UnsupportedOperationException {
			System.out.println("iId = " + itemId + " iName = "
					+ item.getItemProperty("name").getValue());
			return myDateFilter.passesFilter(itemId, item);
		}

		@Override
		public boolean appliesToProperty(Object propertyId) {
			System.out.println("pId = " + propertyId);
			return myDateFilter.appliesToProperty(propertyId);
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
