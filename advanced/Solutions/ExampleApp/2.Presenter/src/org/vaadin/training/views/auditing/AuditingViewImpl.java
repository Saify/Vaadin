package org.vaadin.training.views.auditing;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;

public class AuditingViewImpl extends VerticalLayout implements View,
		AuditingView {

	private AuditingPresenter presenter;

	public AuditingViewImpl() {
		Label header = new Label("Audit Log Messages");
		header.setStyleName(Reindeer.LABEL_H1);
		addComponent(header);
		setMargin(true);

		presenter = new AuditingPresenter();
		presenter.setView(this);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addAuditLog(final String message) {
		addComponent(new Label(message));

	}

}
