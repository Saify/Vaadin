package org.vaadin.training.views.auditing;

import org.vaadin.training.backend.AuditLogService;
import org.vaadin.training.backend.AuditLogService.AuditLogListener;

public class AuditingPresenter implements AuditLogListener {
	private AuditingView view;
	
	public AuditingPresenter() {
		AuditLogService.addAuditLogListener(this);
	}

	@Override
	public void auditLogAdded(final String message) {
		view.addAuditLog(message);
	}

	public void setView(AuditingView auditingView) {
		view = auditingView;
		fetchInitialData();
	}

	private void fetchInitialData() {
		for (String message : AuditLogService.getAuditLogMessages()) {
			view.addAuditLog(message);
		}
	}

}
