package com.vaadin.training.extending.demo.client;

import com.vaadin.shared.communication.ClientRpc;

public interface ResetUploadClientRpc extends ClientRpc {

	public void reset();

}
