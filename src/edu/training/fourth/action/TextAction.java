package edu.training.fourth.action;

import edu.training.fourth.composite.Component;
import edu.training.fourth.composite.Composite;
import edu.training.fourth.composite.Symbol;
import edu.training.fourth.exception.SymbolUnsupportedOperationException;

import edu.training.fourth.type.Part;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Roman on 13.11.2016.
 */
public class TextAction {
    private static final Logger LOG = LogManager.getLogger();

    public Component sortTextByLexemes(Component root) {
        try {
            List<Component> sentences = new ArrayList<>();
            root.getComponents().stream().forEach(s -> sentences.addAll(((Composite)s).getComponents()));

            sentences.sort((o1, o2) -> ((Composite)o1).getComponents().size() - ((Composite)o2).getComponents().size());

            Component sortedSentences = new Composite(Part.PARAGRAPH);
            sortedSentences.getComponents().addAll(sentences);
            return sortedSentences;
        }catch (SymbolUnsupportedOperationException e){
            LOG.error(e);
        }
        return root;
    }

    public Component swapLexemesInSentences(Component root) {
        try {
            Component swappedRoot = root.clone();
            for (Component paragraph : swappedRoot.getComponents()) {
                paragraph.getComponents().stream().forEach(s -> Collections.swap(((Composite)s).getComponents(), 0, ((Composite)s).getComponents().size() - 1));
            }
            return swappedRoot;
        } catch (CloneNotSupportedException e) {
            LOG.error(e);
        }catch (SymbolUnsupportedOperationException e){
            LOG.error(e);
        }
        return root;
    }

    public Component convertLexemesInText(Component root){
        try {
            Component convertedRoot = root.clone();
            for (Component paragraph : convertedRoot.getComponents()) {
                for(Component sentence : paragraph.getComponents()) {
                    for(Component lexeme: sentence.getComponents()){
                        for(Component comp : lexeme.getComponents()){
                            if(comp instanceof Symbol){
                                continue;
                            }
                            Iterator<Component> symbIterator = comp.getComponents().iterator();
                            Symbol symbol = (Symbol)symbIterator.next();
                            while(symbIterator.hasNext()){
                                if(symbIterator.next().equals(symbol)) {
                                    symbIterator.remove();
                                }
                            }
                        }
                    }
                }
            }
            return convertedRoot;
        }catch (CloneNotSupportedException e){
            LOG.error(e);
        }catch (SymbolUnsupportedOperationException e){
            LOG.error(e);
        }
        return root;
    }

}
