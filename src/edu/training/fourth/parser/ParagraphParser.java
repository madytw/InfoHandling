package edu.training.fourth.parser;

import edu.training.fourth.composite.Component;
import edu.training.fourth.exception.SymbolUnsupportedOperationException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by Roman on 02.11.2016.
 */
public class ParagraphParser extends AbstractParser {

    private static final String PARAGRAPH_REGEX = "(\t| )+.*\n";
    public ParagraphParser(AbstractParser next) {
        super(next);
    }

    @Override
    public Component parsePart(Component root, String text) {
        Pattern paragraphPattern = Pattern.compile(PARAGRAPH_REGEX);
        try {
            Matcher paragraphMatcher = paragraphPattern.matcher(text);
            while (paragraphMatcher.find()) {
                String s = paragraphMatcher.group();
                root.add(next.parsePart(root, s));
            }
        }catch (SymbolUnsupportedOperationException e){
            LOG.error(e);
        }
        return root;
    }

    public String collectPart(Component root) {
        try {
            return root.getComponents().stream().map(c -> next.collectPart(c)).map(c -> c.concat("\n")).collect(Collectors.joining());
        }catch (SymbolUnsupportedOperationException e){
            LOG.error(e);
        }
        return null;
    }
}
