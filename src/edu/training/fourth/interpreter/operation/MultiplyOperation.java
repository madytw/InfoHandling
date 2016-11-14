package edu.training.fourth.interpreter.operation;

import edu.training.fourth.interpreter.ExpressionContex;
import edu.training.fourth.type.Priority;

/**
 * Created by Roman on 12.11.2016.
 */
public class MultiplyOperation extends AbstractOperation {
    public MultiplyOperation() {
        super(Priority.NORMAL);
    }

    @Override
    public void calculate(ExpressionContex contex) {
        contex.pushValue(contex.popValue() * contex.popValue());
    }
}
