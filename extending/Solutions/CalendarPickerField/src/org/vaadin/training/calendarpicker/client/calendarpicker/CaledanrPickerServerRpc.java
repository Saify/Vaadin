package org.vaadin.training.calendarpicker.client.calendarpicker;

import java.util.Date;

import com.vaadin.shared.communication.ServerRpc;

public interface CaledanrPickerServerRpc extends ServerRpc {

    // TODO example API
    public void setDate(Date newValue);

}
