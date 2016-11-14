package edu.training.fourth.interpreter.operation;

import edu.training.fourth.interpreter.ExpressionContex;
import edu.training.fourth.type.Priority;

/**
 * Created by Roman on 12.11.2016.
 */
public class PlusOperation extends AbstractOperation {

    public PlusOperation() {
        super(Priority.LOW);
    }

    @Override
    public void calculate(ExpressionContex contex) {
        contex.pushValue(contex.popValue() + contex.popValue());
    }

}
