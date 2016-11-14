package edu.training.fourth.interpreter.operation;

import edu.training.fourth.interpreter.ExpressionContex;
import edu.training.fourth.type.Priority;

/**
 * Created by Roman on 12.11.2016.
 */
public class DivideOperation extends AbstractOperation {
    public DivideOperation() {
        super(Priority.NORMAL);
    }

    @Override
    public void calculate(ExpressionContex contex) {
        double sec = contex.popValue();
        double fir = contex.popValue();
        contex.pushValue(fir / sec);
    }
}
