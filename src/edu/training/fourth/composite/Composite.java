package edu.training.fourth.composite;

import edu.training.fourth.type.Part;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Roman on 02.11.2016.
 */
public class Composite implements Component{

    private List<Component> components = new ArrayList<Component>();
    private Part part;

    public Composite(Part part) {
        this.part = part;
    }

    @Override
    public void add(Component c) {
        components.add(c);
    }

    @Override
    public List<Component> getComponents() {
        return components;
    }

    @Override
    public Composite clone() throws CloneNotSupportedException {
        Composite copy = new Composite(part);
        copy.components = new ArrayList<>();
        for(Component c : components){
                copy.components.add(c.clone());
        }
        return copy;
    }
}
