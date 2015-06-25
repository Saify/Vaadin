package org.vaadin.training.backend;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.WeakHashMap;

import org.vaadin.training.authentication.AuthenticationService;
import org.vaadin.training.authentication.User;

public class AuditLogService {

	private static List<String> auditMessages = new ArrayList<String>();

	// Weak collection
	private static Set<AuditLogListener> weakHashSet = Collections
			.newSetFromMap(new WeakHashMap<AuditLogListener, Boolean>());

	public static void addAuditLogEntry(String message) {
		User user = AuthenticationService.getLoggedInUser();
		String auditMessage = new Date().toString() + ": " + user.getUserName()
				+ ": " + message;
		synchronized (auditMessages) {
			auditMessages.add(auditMessage);
		}

		fireEvent(auditMessage);
	}

	private static void fireEvent(String message) {
		synchronized (weakHashSet) {
			for (AuditLogListener listener : weakHashSet) {
				listener.auditLogAdded(message);
			}
		}
	}

	public static List<String> getAuditLogMessages() {
		return Collections.unmodifiableList(auditMessages);
	}

	public static void addAuditLogListener(AuditLogListener listener) {
		weakHashSet.add(listener);
	}

	public interface AuditLogListener {

		public void auditLogAdded(String message);

	}

}
