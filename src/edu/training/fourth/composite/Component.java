package edu.training.fourth.composite;

import edu.training.fourth.exception.SymbolUnsupportedOperationException;
import edu.training.fourth.type.Part;

import java.util.List;

/**
 * Created by Roman on 02.11.2016.
 */
public interface Component extends Cloneable{
    void add(Component c) throws SymbolUnsupportedOperationException;
    List<Component> getComponents() throws SymbolUnsupportedOperationException;
    Component clone() throws CloneNotSupportedException;
}
