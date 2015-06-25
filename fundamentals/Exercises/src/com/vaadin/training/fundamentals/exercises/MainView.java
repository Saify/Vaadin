package com.vaadin.training.fundamentals.exercises;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ExternalResource;
import com.vaadin.training.fundamentals.exercises.ex1.Polling;
import com.vaadin.training.fundamentals.exercises.ex2.NavigationBar;
import com.vaadin.training.fundamentals.exercises.ex3.ApplicationLayout;
import com.vaadin.training.fundamentals.exercises.ex4.BindingProperties;
import com.vaadin.training.fundamentals.exercises.ex5.DataValidation;
import com.vaadin.training.fundamentals.exercises.ex6.ProductForm;
import com.vaadin.training.fundamentals.exercises.ex7.PopulateContainer;
import com.vaadin.training.fundamentals.exercises.ex8.ContainerFilters;
import com.vaadin.training.fundamentals.exercises.ex9.DataBinding;
import com.vaadin.training.fundamentals.practices.pr1.MyEventsExample;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Link;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.VerticalSplitPanel;
import com.vaadin.ui.themes.Reindeer;

public class MainView extends VerticalLayout implements View {

	private static final long serialVersionUID = 1L;
	private Navigator navigator;

	public MainView(Navigator navigator) {
		this.navigator = navigator;
		setSpacing(true);

		int i = 1;
		HorizontalSplitPanel vsp = new HorizontalSplitPanel();
		VerticalLayout top = new VerticalLayout();
		top.addComponent(createExerciseLink(i++, "Polling", Polling.class));
		top.addComponent(createExerciseLink(i++, "Navigation bar",
				NavigationBar.class));
		top.addComponent(createExerciseLink(i++, "Application layout",
				ApplicationLayout.class));
		top.addComponent(createExerciseLink(i++, "Binding properties",
				BindingProperties.class));
		top.addComponent(createExerciseLink(i++, "Validators",
				DataValidation.class));
		top.addComponent(createExerciseLink(i++, "FieldGroups",
				ProductForm.class));
		top.addComponent(createExerciseLink(i++, "Populating a container",
				PopulateContainer.class));
		top.addComponent(createExerciseLink(i++,
				"Applying filters on a container", ContainerFilters.class));
		top.addComponent(createExerciseLink(i++, "Data binding",
				DataBinding.class));
		top.addComponent(new Link("Ex " + i++ + ": Creating a theme",
				new ExternalResource("app://sass")));

		VerticalLayout bottom = new VerticalLayout();
		bottom.addComponent(createExerciseLink(i++, "My Event Example",
				MyEventsExample.class));

		
		vsp.addComponents(top, bottom);
		setSizeFull();
		addComponent(vsp);
	}

	private Button createExerciseLink(int exNum, String caption,
			Class<? extends View> viewClass) {
		navigator.addView(caption.replace(" ", ""), viewClass);
		Button button = new Button("Ex " + exNum + ": " + caption,
				new ClickListener() {

					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						navigator.navigateTo(event.getButton().getData()
								.toString().replace(" ", ""));
					}
				});
		button.setData(caption);
		button.setStyleName(Reindeer.BUTTON_LINK);

		return button;
	}

	@Override
	public void enter(ViewChangeEvent event) {

	}

}
