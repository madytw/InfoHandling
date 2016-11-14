package edu.training.fourth.interpreter;

import java.util.ArrayDeque;

/**
 * Created by Roman on 12.11.2016.
 */
public class ExpressionContex {
    private ArrayDeque<Double> contextValues = new ArrayDeque<>();

    public Double popValue() {
        return contextValues.pop();
    }

    public void pushValue(double d){
        contextValues.push(d);
    }
}
