package org.vaadin.training.views.auditing;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import com.vaadin.cdi.CDIView;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;

@CDIView("auditing")
public class AuditingViewImpl extends VerticalLayout implements View,
		AuditingView {

	@Inject
	private AuditingPresenter presenter;

	public AuditingViewImpl() {
		Label header = new Label("Audit Log Messages");
		header.setStyleName(Reindeer.LABEL_H1);
		addComponent(header);
		setMargin(true);
	}
	
	@PostConstruct
	private void init() {
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
