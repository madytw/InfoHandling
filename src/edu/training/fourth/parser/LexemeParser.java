package edu.training.fourth.parser;

import edu.training.fourth.composite.Component;
import edu.training.fourth.composite.Composite;
import edu.training.fourth.composite.Symbol;
import edu.training.fourth.exception.SymbolUnsupportedOperationException;
import edu.training.fourth.interpreter.ExpressionTranslator;
import edu.training.fourth.type.Part;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by Roman on 03.11.2016.
 */
public class LexemeParser extends AbstractParser {
    private static final String LEXEME_REGEX = "\\s\\S+[\\s|.|?|!]";
    private static final String MATH_EXPRESSION = "[\\d|(|+|-]+[*|/|\\d|(|)|+|-]*[)|\\d|+|-]";

    public LexemeParser(AbstractParser next) {
        super(next);
    }

    @Override
    public Component parsePart(Component root, String text) {
        Component sentence = new Composite(Part.SENTENCE);
        try {
            ExpressionTranslator translator = new ExpressionTranslator();

            Pattern lexemePattern = Pattern.compile(LEXEME_REGEX);
            Pattern mathPattern = Pattern.compile(MATH_EXPRESSION);

            Matcher lexemeMatcher = lexemePattern.matcher(text);
            Matcher mathMatcher = null;

            while (lexemeMatcher.find()) {
                int index = lexemeMatcher.end();
                String s = lexemeMatcher.group();
                mathMatcher = mathPattern.matcher(s);

                if (mathMatcher.find()) {
                    String expression = mathMatcher.group();
                    double d = translator.calculate(expression);
                    s = s.replace(expression, String.valueOf((int) d));
                }
                sentence.add(next.parsePart(sentence, s));
                lexemeMatcher.region(index - 1, text.length());
            }
        }catch (SymbolUnsupportedOperationException e){
            LOG.error(e);
        }
        return sentence;
    }

    public String collectPart(Component root) {
        try {
            List<Component> sentence = root.getComponents();
            if (sentence.get(sentence.size() - 1).equals(new Symbol(' '))) {
                return sentence.stream().limit(sentence.size() - 1).map(c -> next.collectPart(c)).collect(Collectors.joining());
            }
            return sentence.stream().map(c -> next.collectPart(c)).collect(Collectors.joining());
        }catch (SymbolUnsupportedOperationException e){
            LOG.error(e);
        }
        return null;
    }
}
