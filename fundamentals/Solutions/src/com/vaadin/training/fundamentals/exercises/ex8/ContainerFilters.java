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
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.DateField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class ContainerFilters extends CustomComponent implements View {

	private Container.Filterable container;
	private DateField startDate;
	private DateField endDate;

	public ContainerFilters() {
		VerticalLayout layout = new VerticalLayout();
		layout.addComponent(createFilterComponents());

		Table table = new Table();
		container = ContainerHelper.createProductContainer();
		table.setContainerDataSource(container);
		layout.addComponent(table);
		setCompositionRoot(layout);
	}

	private Layout createFilterComponents() {
		HorizontalLayout layout = new HorizontalLayout();
		layout.setSpacing(true);

		startDate = new PopupDateField("Start");
		startDate.setResolution(Resolution.DAY);

		endDate = new PopupDateField("End");
		endDate.setResolution(Resolution.DAY);

		layout.addComponent(startDate);
		layout.addComponent(endDate);

		Component filterButton = new Button("Filter",
				new Button.ClickListener() {
					@Override
					public void buttonClick(ClickEvent event) {
						container.removeAllContainerFilters();
						Date start = startDate.getValue();
						Date end = endDate.getValue();
						container.addContainerFilter(new DateRangeFilter(
								getCleanDate(start), getCleanDate(end)));
					}
				});
		layout.addComponent(filterButton);
		layout.setComponentAlignment(filterButton, Alignment.BOTTOM_LEFT);

		return layout;
	}

	private static class DateRangeFilter implements Filter {

		private final Filter wrappedFilter;

		public DateRangeFilter(Date startDate, Date endDate) {

			if (startDate == null && endDate == null) {
				wrappedFilter = new Compare.GreaterOrEqual("available",
						new Date(0L));
			} else if (startDate == null) {
				wrappedFilter = new Compare.LessOrEqual("available", endDate);
			} else if (endDate == null) {
				wrappedFilter = new Compare.GreaterOrEqual("available",
						startDate);
			} else {
				wrappedFilter = new And(new Compare.GreaterOrEqual("available",
						startDate), new Compare.LessOrEqual("available",
						endDate));
			}
		}

		@Override
		public boolean passesFilter(Object itemId, Item item)
				throws UnsupportedOperationException {
			return wrappedFilter.passesFilter(itemId, item);
		}

		@Override
		public boolean appliesToProperty(Object propertyId) {
			return wrappedFilter.appliesToProperty(propertyId);
		}
	}

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
