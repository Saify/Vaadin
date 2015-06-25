package org.vaadin.training.stepper.client.stepper;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.MouseWheelEvent;
import com.google.gwt.event.dom.client.MouseWheelHandler;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.IntegerBox;

public class StepperWidget extends HorizontalPanel implements ClickHandler,
        HasValue<Integer> {

    public static final String CLASSNAME = "stepper";

    private IntegerBox integerBox = new IntegerBox();
    private Button stepUp = new Button("^", this);
    private Button stepDown = new Button("V", this);

    private List<Integer> acceptedKeyCodes = new ArrayList<Integer>();

    public StepperWidget() {
        setStyleName(CLASSNAME);
        add(integerBox);
        acceptedKeyCodes.add((int) '0');
        acceptedKeyCodes.add((int) '1');
        acceptedKeyCodes.add((int) '2');
        acceptedKeyCodes.add((int) '3');
        acceptedKeyCodes.add((int) '4');
        acceptedKeyCodes.add((int) '5');
        acceptedKeyCodes.add((int) '6');
        acceptedKeyCodes.add((int) '7');
        acceptedKeyCodes.add((int) '8');
        acceptedKeyCodes.add((int) '9');
        acceptedKeyCodes.add(KeyCodes.KEY_BACKSPACE);
        acceptedKeyCodes.add(KeyCodes.KEY_DELETE);
        acceptedKeyCodes.add(KeyCodes.KEY_LEFT);
        acceptedKeyCodes.add(KeyCodes.KEY_RIGHT);

        integerBox.addKeyDownHandler(new KeyDownHandler() {
            @Override
            public void onKeyDown(KeyDownEvent event) {
                if (!acceptedKeyCodes.contains(event.getNativeKeyCode())) {
                    event.preventDefault();
                }
            }
        });
        integerBox.addMouseWheelHandler(new MouseWheelHandler() {

            @Override
            public void onMouseWheel(MouseWheelEvent event) {
                if (event.isSouth()) {
                    decreaseByOne();
                } else {
                    increaseByOne();
                }

            }
        });

        stepUp.setWidth("20px");
        stepUp.setHeight("100%");
        add(stepUp);

        stepDown.setWidth("20px");
        stepDown.setHeight("100%");
        add(stepDown);
    }

    @Override
    public void onClick(ClickEvent event) {
        if (event.getSource().equals(stepUp)) {
            increaseByOne();
        } else if (event.getSource().equals(stepDown)) {
            decreaseByOne();
        }
    }

    private void decreaseByOne() {
        Integer value = integerBox.getValue();
        value--;
        integerBox.setValue(value, true);
    }

    private void increaseByOne() {
        Integer value = integerBox.getValue();
        value++;
        integerBox.setValue(value, true);
    }

    @Override
    public Integer getValue() {
        return integerBox.getValue();
    }

    @Override
    public void setValue(Integer newValue) {
        integerBox.setValue(newValue);
    }

    @Override
    public void setValue(Integer value, boolean fireEvents) {
        integerBox.setValue(value, fireEvents);
    }

    @Override
    public HandlerRegistration addValueChangeHandler(
            ValueChangeHandler<Integer> handler) {
        return integerBox.addValueChangeHandler(handler);
    }
}
