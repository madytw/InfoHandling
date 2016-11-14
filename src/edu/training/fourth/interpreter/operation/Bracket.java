package edu.training.fourth.interpreter.operation;

import edu.training.fourth.exception.UnsupportedMathOperationException;
import edu.training.fourth.interpreter.ExpressionContex;
import edu.training.fourth.type.Priority;

/**
 * Created by Roman on 12.11.2016.
 */
public class Bracket extends AbstractOperation {

    public Bracket() {
        super(Priority.VERY_LOW);
    }

    @Override
    public void calculate(ExpressionContex contex) throws UnsupportedMathOperationException{
        throw new UnsupportedMathOperationException("No such operation for bracket!");
    }
}
