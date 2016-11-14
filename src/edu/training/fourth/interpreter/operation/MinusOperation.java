package edu.training.fourth.interpreter.operation;

import edu.training.fourth.interpreter.ExpressionContex;
import edu.training.fourth.type.Priority;

/**
 * Created by Roman on 12.11.2016.
 */
public class MinusOperation extends AbstractOperation {
    public MinusOperation() {
        super(Priority.LOW);
    }

    @Override
    public void calculate(ExpressionContex contex) {
        double sec = contex.popValue();
        double fir = contex.popValue();
        contex.pushValue(fir - sec);
    }
}
