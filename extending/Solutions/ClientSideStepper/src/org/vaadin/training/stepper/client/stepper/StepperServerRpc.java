package org.vaadin.training.stepper.client.stepper;

import com.vaadin.shared.communication.ServerRpc;

public interface StepperServerRpc extends ServerRpc {

    public void setValue(Integer value);

}
