package org.vaadin.training.numerictextfield;

import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.data.util.converter.Converter.ConversionException;


public class NumericTextField extends com.vaadin.ui.TextField {


    public NumericTextField() {
    }
    
    @Override
    protected void setValue(String newFieldValue, boolean repaintIsNotNeeded)
    		throws com.vaadin.data.Property.ReadOnlyException,
    		ConversionException, InvalidValueException {
    	if(newFieldValue == null || "".equals(newFieldValue)) {
    		super.setValue(null, repaintIsNotNeeded);
    		return;
    	}
    	try {
    		// Just parse it to see, if an exception occurs
    		Integer.parseInt(newFieldValue);
    		super.setValue(newFieldValue, repaintIsNotNeeded);    		
    	} catch(NumberFormatException e) {
    		throw new InvalidValueException("The value " + newFieldValue + " was not an integer");
    	}
    }

}
