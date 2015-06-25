package org.vaadin.training;


import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * Main UI class
 */
@SuppressWarnings("serial")
public class CssinjectUI extends UI {

	@Override
	protected void init(VaadinRequest request) {
		final VerticalLayout layout = new VerticalLayout();
		layout.setMargin(true);
		setContent(layout);

		final Label label = new Label("Change my style");
		final CssInject inject = new CssInject();
		inject.extend(label);

		layout.addComponent(label);

		Button button = new Button("Background: red");
		button.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				inject.addStyle("background-color", "red");
			}
		});
		layout.addComponent(button);
		layout.addComponent(new Button("Background: green",
				new ClickListener() {

					@Override
					public void buttonClick(ClickEvent event) {
						inject.addStyle("background-color", "#00ff00");
					}
				}));
		layout.addComponent(new Button("Border: black", new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				inject.addStyle("border", "solid 1px #000000");
			}
		}));
		layout.addComponent(new Button("Remove border", new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				inject.removeStyle("border");
			}
		}));

		HorizontalLayout hl = new HorizontalLayout();
		hl.setSpacing(true);
		final TextField property = new TextField("CSS property");
		hl.addComponent(property);

		final TextField propertyValue = new TextField("Value");
		hl.addComponent(propertyValue);

		hl.addComponent(new Button("Set", new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				inject.addStyle(property.getValue(), propertyValue.getValue());
			}
		}));

		layout.addComponent(hl);
	}

}
