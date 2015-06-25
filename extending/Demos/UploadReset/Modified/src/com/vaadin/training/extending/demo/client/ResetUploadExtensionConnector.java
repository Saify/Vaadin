package com.vaadin.training.extending.demo.client;

import com.google.gwt.user.client.Element;
import com.vaadin.client.ServerConnector;
import com.vaadin.client.extensions.AbstractExtensionConnector;
import com.vaadin.client.ui.VUpload;
import com.vaadin.client.ui.upload.UploadConnector;
import com.vaadin.shared.ui.Connect;
import com.vaadin.training.extending.demo.ResetUploadExtension;

@Connect(ResetUploadExtension.class)
public class ResetUploadExtensionConnector extends AbstractExtensionConnector {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7617304417473277786L;

	public ResetUploadExtensionConnector() {
		registerRpc(ResetUploadClientRpc.class, new ResetUploadClientRpc() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 3574580099626921634L;

			@Override
			public void reset() {
				UploadConnector uploadConnector = (UploadConnector) getParent();
				VUpload vUpload = uploadConnector.getWidget();
				Element element = vUpload.getElement();
				nativelyReset(element);
			}
		});
	}

	@Override
	protected void extend(ServerConnector target) {
	}
	
	private native void nativelyReset(Element form) /*-{
		form.reset();
	}-*/;

}
