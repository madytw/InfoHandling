package edu.training.fourth.interpreter;

import edu.training.fourth.exception.UnsupportedMathOperationException;
import edu.training.fourth.interpreter.operation.*;
import edu.training.fourth.interpreter.operation.Number;
import edu.training.fourth.type.Priority;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayDeque;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static edu.training.fourth.interpreter.RegexConstantContainer.*;

/**
 * Created by Roman on 12.11.2016.
 */
public class ExpressionTranslator {

    private ExpressionContex context = new ExpressionContex();
    private ArrayDeque<AbstractOperation> stack;
    private ArrayDeque<AbstractOperation> rpn;

    private static final Logger LOG = LogManager.getLogger();
    private static final Pattern NUM_PATTERN = Pattern.compile(NUM_REGEX);
    private Matcher numMatcher;

    public ExpressionTranslator() {
        stack = new ArrayDeque<>();
        rpn = new ArrayDeque<>();
    }

    private void translate(String expression) {
        expression = prepareExpressionForTranslation(expression);
        numMatcher = NUM_PATTERN.matcher(expression);
        int i = 0;
        while (i != expression.length()) {
            char curr = expression.charAt(i);
            switch (curr) {
                case '+':
                    addOperation(new PlusOperation());
                    i++;
                    break;
                case '-':
                    addOperation(new MinusOperation());
                    i++;
                    break;
                case '*':
                    addOperation(new MultiplyOperation());
                    i++;
                    break;
                case '/':
                    addOperation(new DivideOperation());
                    i++;
                    break;
                case 'i':
                    addUnaryOperation(new IncOperation(), expression, i);
                    i++;
                    break;
                case 'd':
                    addUnaryOperation(new DecOperation(), expression, i);
                    i++;
                    break;
                case 'm':
                    addOperation(new UnaryMinusOperation());
                    i++;
                    break;
                case '(':
                    addOperation(new Bracket());
                    i++;
                    break;
                case ')':
                    polling();
                    i++;
                    break;
                default:
                    i += addNumber();
                    break;

            }
        }
        while (!stack.isEmpty()) {
            rpn.addLast(stack.pollLast());
        }
    }

    private String prepareExpressionForTranslation(String expression) {
        rpn.clear();
        expression = expression.replaceAll(INC, INC_SUB);
        expression = expression.replaceAll(DEC, DEC_SUB);
        StringBuffer sb = new StringBuffer(expression);
        Pattern minusPatter = Pattern.compile(MINUS);
        Matcher minusMatcher = minusPatter.matcher(sb);
        while (minusMatcher.find()) {
            int index = minusMatcher.start();
            if (index == 0) {
                sb.setCharAt(index, UNARY_MINUS_SUB.charAt(0));
                continue;
            }
            char prevSymb = sb.charAt(index - 1);
            if (prevSymb != ')' && !Character.isDigit(prevSymb) && prevSymb != 'i' && prevSymb != 'd') {
                sb.setCharAt(index, UNARY_MINUS_SUB.charAt(0));
            }
        }
        return sb.toString();
    }

    private void addOperation(AbstractOperation operation) {
        if (operation.getPriority() == Priority.VERY_LOW) {
            stack.addLast(operation);
            return;
        }
        while (stack.peekLast() != null && operation.getPriority().getPriority() <= stack.peekLast().getPriority().getPriority()) {
            rpn.addLast(stack.pollLast());
        }
        stack.addLast(operation);
    }

    private void addUnaryOperation(AbstractOperation operation, String expression, int i) {
        if (i > 0 && !Character.isDigit(expression.charAt(i - 1))) {
            addOperation(operation);
        }
    }

    private void polling() {
        while (stack.peekLast().getPriority() != Priority.VERY_LOW) {
            rpn.addLast(stack.pollLast());
        }
        stack.removeLast();
    }

    private int addNumber() {
        numMatcher.find();
        String numString = numMatcher.group();
        int numberLength = numString.length();
        rpn.addLast(new Number(Integer.parseInt(numString)));
        return numberLength;
    }

    public double calculate(String expression) {
        translate(expression);
        for (AbstractOperation op : rpn) {
            try {
                op.calculate(context);
            } catch (UnsupportedMathOperationException e) {
                LOG.error(e);
            }
        }
        return context.popValue();
    }
}
