package org.vaadin.training.views.auditing;

import org.vaadin.training.backend.AuditLogService;

public class AuditingPresenter {
	private AuditingView view;

	public AuditingPresenter() {
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
