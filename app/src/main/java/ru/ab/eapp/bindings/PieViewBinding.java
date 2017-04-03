package ru.ab.eapp.bindings;

import org.robobinding.annotation.ViewBinding;
import org.robobinding.customviewbinding.CustomViewBinding;

import ru.ab.eapp.ui.controls.PieView;

/**
 * Created by arturh on 03.04.2017.
 */

@ViewBinding(simpleOneWayProperties = {"start", "end", "color"})
public class PieViewBinding extends CustomViewBinding<PieView> {
}
