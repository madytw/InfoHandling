package edu.training.fourth.parser;

import edu.training.fourth.composite.Component;
import edu.training.fourth.composite.Composite;
import edu.training.fourth.exception.SymbolUnsupportedOperationException;
import edu.training.fourth.type.Part;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by Roman on 02.11.2016.
 */
public class SentenceParser extends AbstractParser {
    private static final String SENTENCE_REGEX = "\\s[A-Z]+[^\\.!\\?]*[\\.!\\?]+";

    public SentenceParser(AbstractParser next) {
        super(next);
    }

    @Override
    public Component parsePart(Component root, String text) {
        Component paragraph = new Composite(Part.PARAGRAPH);
        try {
            Pattern sentencePattern = Pattern.compile(SENTENCE_REGEX);
            Matcher sentenceMatcher = sentencePattern.matcher(text);
            while (sentenceMatcher.find()) {
                String s = sentenceMatcher.group();
                paragraph.add(next.parsePart(paragraph, s));
            }
        }catch (SymbolUnsupportedOperationException e){
            LOG.error(e);
        }
        return paragraph;
    }
    public String collectPart(Component root) {
        try {
            return root.getComponents().stream().map(c -> next.collectPart(c)).collect(Collectors.joining());
        }catch (SymbolUnsupportedOperationException e){
            LOG.error(e);
        }
        return null;
    }
}
