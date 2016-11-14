package edu.training.fourth.parser;

import edu.training.fourth.composite.Component;
import edu.training.fourth.composite.Composite;
import edu.training.fourth.composite.Symbol;
import edu.training.fourth.exception.SymbolUnsupportedOperationException;
import edu.training.fourth.type.Part;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Roman on 03.11.2016.
 */
public class WordParser extends AbstractParser {

    private static final String PUNCTUATION_REGEX = "[,|'|:|;|.|?|!|\\s]";
    private static final String WORD_REGEX = "[A-Z|a-z[-?\\d]|—]+";

    public WordParser(AbstractParser next) {
        super(next);
    }

    @Override
    public Component parsePart(Component root, String text) {
        Component lexeme = new Composite(Part.LEXEME);
        try {
            Pattern wordPattern = Pattern.compile(WORD_REGEX);
            Matcher wordMatcher = wordPattern.matcher(text);

            Pattern punctPattern = Pattern.compile(PUNCTUATION_REGEX);
            Matcher punctMatcher = null;

            if (wordMatcher.find()) {
                String s = wordMatcher.group();
                int index = wordMatcher.start();

                punctMatcher = punctPattern.matcher(text);
                punctMatcher.region(0, index);

                while (punctMatcher.find()) {
                    String p = punctMatcher.group();
                    lexeme.add(new Symbol(p.charAt(0)));
                }

                lexeme.add(next.parsePart(lexeme, s));

                punctMatcher.region(index + s.length(), text.length());
                while (punctMatcher.find()) {
                    String p = punctMatcher.group();
                    lexeme.add(new Symbol(p.charAt(0)));
                }
            }
        }catch (SymbolUnsupportedOperationException e){
            LOG.error(e);
        }
        return lexeme;
    }

    @Override
    public String collectPart(Component root) {
        StringBuffer sb = new StringBuffer();
        try {
            if (root instanceof Symbol) {
                return sb.append(next.collectPart(root)).toString();
            }
            root.getComponents().stream().forEach(c -> sb.append(next.collectPart(c)));
            if (sb.charAt(sb.length() - 1) == ' ') {
                sb.deleteCharAt(sb.length() - 1);
            }
        }catch (SymbolUnsupportedOperationException e){
            LOG.error(e);
        }
        return sb.toString();
    }
}
