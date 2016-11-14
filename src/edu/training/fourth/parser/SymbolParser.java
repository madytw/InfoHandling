package edu.training.fourth.parser;

import edu.training.fourth.composite.Component;
import edu.training.fourth.composite.Composite;
import edu.training.fourth.composite.Symbol;
import edu.training.fourth.exception.SymbolUnsupportedOperationException;
import edu.training.fourth.type.Part;

import java.util.stream.Collectors;

/**
 * Created by Roman on 03.11.2016.
 */
public class SymbolParser extends AbstractParser {

    public SymbolParser(){
        super(null);
    }

    @Override
    public Component parsePart(Component root, String text) {
        Component word = new Composite(Part.WORD);
        try {
            char[] chars = text.toCharArray();
            for (char c : chars) {
                word.add(new Symbol(c));
            }
        }catch (SymbolUnsupportedOperationException e){
            LOG.error(e);
        }
        return word;
    }

    @Override
    public String collectPart(Component root) {
        try {
            if (root instanceof Symbol) {
                return root.toString();
            }
            return root.getComponents().stream().map(c -> c.toString()).collect(Collectors.joining());
        }catch (SymbolUnsupportedOperationException e){
            LOG.error(e);
        }
        return null;
    }
}
