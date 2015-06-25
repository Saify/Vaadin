package org.vaadin.training.client.refresher;

import com.vaadin.shared.communication.ServerRpc;

public interface RefresherServerRpc extends ServerRpc {

	public void refresh();
}
