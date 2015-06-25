package org.vaadin.training.client.cssinject;

import java.util.HashSet;
import java.util.Set;

import org.vaadin.training.CssInject;

import com.google.gwt.user.client.Element;
import com.vaadin.client.ComponentConnector;
import com.vaadin.client.ServerConnector;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.extensions.AbstractExtensionConnector;
import com.vaadin.shared.ui.Connect;

@Connect(CssInject.class)
public class CssInjectConnector extends AbstractExtensionConnector {

	private static final long serialVersionUID = 1L;

	private Element element;

	private Set<String> properties = new HashSet<String>();

	@Override
	public CssInjectState getState() {
		return (CssInjectState) super.getState();
	}

	@Override
	public void onStateChanged(StateChangeEvent stateChangeEvent) {
		super.onStateChanged(stateChangeEvent);

		Set<String> newProperties = getState().css.keySet();

		// Remove from DOM any removed properties
		for (String property : properties) {
			if (!newProperties.contains(property)) {
				element.getStyle().setProperty(property, null);
			}
		}

		// Update and add properties
		for (String property : getState().css.keySet()) {
			element.getStyle().setProperty(property,
					getState().css.get(property));
		}

		properties = newProperties;

	}

	@Override
	protected void extend(ServerConnector target) {
		element = ((ComponentConnector) target).getWidget().getElement();
	}

}
