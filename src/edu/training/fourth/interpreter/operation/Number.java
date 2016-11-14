package edu.training.fourth.interpreter.operation;

import edu.training.fourth.interpreter.ExpressionContex;

/**
 * Created by Roman on 12.11.2016.
 */
public class Number extends AbstractOperation {
    private int number;

    public Number(int number) {
        super(null);
        this.number = number;
    }

    @Override
    public void calculate(ExpressionContex contex) {
        contex.pushValue(number);
    }
}
