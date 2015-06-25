package com.vaadin.training.extending.demo;

import com.vaadin.server.AbstractExtension;
import com.vaadin.training.extending.demo.client.ResetUploadClientRpc;
import com.vaadin.ui.Upload;

public class ResetUploadExtension extends AbstractExtension {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7371629305645212254L;

	public ResetUploadExtension(Upload target) {
		super();
		super.extend(target);
	}

	public void resetUpload() {
		ResetUploadClientRpc rpcProxy = getRpcProxy(ResetUploadClientRpc.class);
		rpcProxy.reset();
	}
}
