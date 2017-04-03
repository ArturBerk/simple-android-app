package ru.ab.eapp.vm;

import org.robobinding.presentationmodel.HasPresentationModelChangeSupport;
import org.robobinding.presentationmodel.PresentationModelChangeSupport;

/**
 * Created by Berk on 09.03.2017.
 */
public class BaseViewModel implements HasPresentationModelChangeSupport {

    PresentationModelChangeSupport modelChange = new PresentationModelChangeSupport(this);

    protected void fireChanged(String property) {
        modelChange.firePropertyChange(property);
    }

    @Override
    public PresentationModelChangeSupport getPresentationModelChangeSupport() {
        return modelChange;
    }
}
