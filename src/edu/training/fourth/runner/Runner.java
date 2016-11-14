package edu.training.fourth.runner;

import edu.training.fourth.action.TextAction;
import edu.training.fourth.composite.Component;
import edu.training.fourth.composite.Composite;
import edu.training.fourth.parser.*;
import edu.training.fourth.reader.TextReader;
import edu.training.fourth.type.Part;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by Roman on 03.11.2016.
 */
public class Runner {
    private static final Logger LOG = LogManager.getLogger();
    public static void main(String... args){
        TextReader tr = new TextReader();
        TextAction ta = new TextAction();
        Component root = new Composite(Part.TEXT);

        AbstractParser paragraphParser = new ParagraphParser(new SentenceParser(new LexemeParser(new WordParser(new SymbolParser()))));
        paragraphParser.parse(root, tr.readText());

        LOG.info(paragraphParser.collect(root));// our text
        LOG.info(paragraphParser.collect(ta.sortTextByLexemes(root)));// sorted text by amount of lexemes
        LOG.info(paragraphParser.collect(ta.swapLexemesInSentences(root)));// text with swapped first and last lexeme in sentence
        LOG.info(paragraphParser.collect(ta.convertLexemesInText(root)));// lexemes without characters that match the first

    }
}
