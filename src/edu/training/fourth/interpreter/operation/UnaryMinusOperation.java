package edu.training.fourth.interpreter.operation;

import edu.training.fourth.interpreter.ExpressionContex;
import edu.training.fourth.type.Priority;

/**
 * Created by Roman on 14.11.2016.
 */
public class UnaryMinusOperation extends AbstractOperation {
    public UnaryMinusOperation() {
        super(Priority.VERY_HIGH);
    }

    @Override
    public void calculate(ExpressionContex contex) {
        contex.pushValue(-(contex.popValue()));
    }
}
