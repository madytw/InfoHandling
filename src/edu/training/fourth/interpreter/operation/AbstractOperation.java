package edu.training.fourth.interpreter.operation;

import edu.training.fourth.exception.UnsupportedMathOperationException;
import edu.training.fourth.interpreter.ExpressionContex;
import edu.training.fourth.type.Priority;

/**
 * Created by Roman on 12.11.2016.
 */
public abstract class AbstractOperation {
    private Priority priority;

    public AbstractOperation(Priority priority) {
        this.priority = priority;
    }

    public Priority getPriority() {
        return priority;
    }

    public abstract void calculate(ExpressionContex contex) throws UnsupportedMathOperationException;
}
