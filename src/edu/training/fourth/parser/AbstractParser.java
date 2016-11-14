package edu.training.fourth.parser;

import edu.training.fourth.composite.Component;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by Roman on 02.11.2016.
 */
public abstract class AbstractParser {
    protected AbstractParser next;
    protected static final Logger LOG = LogManager.getLogger();

    public AbstractParser(AbstractParser next) {
        this.next = next;
    }

    public Component parse(Component root, String text){
        if(!text.isEmpty()){
            root = parsePart(root,text);
        }
        return root;
    }

    public String collect(Component root){
        return collectPart(root);
    }
    public abstract Component parsePart(Component root, String text);
    public abstract String collectPart(Component root);
}
