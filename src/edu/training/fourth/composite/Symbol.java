package edu.training.fourth.composite;

import edu.training.fourth.exception.SymbolUnsupportedOperationException;


import java.util.List;

/**
 * Created by Roman on 02.11.2016.
 */
public class Symbol implements Component{
    private char symb;

    public Symbol(char symb) {
        this.symb = symb;
    }

    @Override
    public void add(Component c) throws SymbolUnsupportedOperationException{
        throw new SymbolUnsupportedOperationException("Unsupported operation!");
    }

    public char getSymb() {
        return symb;
    }

    @Override
    public List<Component> getComponents() throws SymbolUnsupportedOperationException{
        throw new SymbolUnsupportedOperationException("Unsupported operation!");
    }

    @Override
    public String toString() {
        return String.valueOf(symb);
    }


    @Override
    public Symbol clone() throws CloneNotSupportedException {
        return (Symbol)super.clone();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Symbol symbol = (Symbol) o;


        return Character.toLowerCase(symb) == Character.toLowerCase(symbol.symb);

    }

    @Override
    public int hashCode() {
        return (int) symb;
    }
}
