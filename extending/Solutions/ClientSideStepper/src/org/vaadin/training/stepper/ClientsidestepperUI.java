package org.vaadin.training.stepper;

import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;

public class ClientsidestepperUI extends UI {
    @Override
    public void init(VaadinRequest request) {
        setContent(new Stepper());
    }

}
