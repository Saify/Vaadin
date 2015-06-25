package org.vaadin.training;

import org.vaadin.training.client.cssinject.CssInjectState;

import com.vaadin.server.AbstractExtension;
import com.vaadin.ui.AbstractComponent;

public class CssInject extends AbstractExtension {

	@Override
	public CssInjectState getState() {
		return (CssInjectState) super.getState();
	}

	public void extend(AbstractComponent target) {
		super.extend(target);
	}

	public void addStyle(String property, String value) {
		getState().css.put(property, value);
	}

	public void removeStyle(String property) {
		getState().css.remove(property);
	}
}
