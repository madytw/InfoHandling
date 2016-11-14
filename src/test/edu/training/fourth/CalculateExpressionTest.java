package test.edu.training.fourth;

import edu.training.fourth.interpreter.ExpressionTranslator;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Roman on 13.11.2016.
 */
public class CalculateExpressionTest {

    @Test
    public void calcTest1() {
        ExpressionTranslator translator = new ExpressionTranslator();
        double actual = translator.calculate("3++-2*9--+3");
        double expected = -12;
        Assert.assertEquals(expected, actual, 0.0);
    }
    @Test
    public void calcText2() {
        ExpressionTranslator translator = new ExpressionTranslator();
        double actual = translator.calculate("(3++-2)*9--+3");
        double expected = 12;
        Assert.assertEquals(expected, actual, 0.0);
    }
    @Test
    public void calcTest3() {
        ExpressionTranslator translator = new ExpressionTranslator();
        double actual = translator.calculate("(++3-2)*9--+3");
        double expected = 21;
        Assert.assertEquals(expected, actual, 0.0);
    }
    @Test
    public void calcTest4() {
        ExpressionTranslator translator = new ExpressionTranslator();
        double actual = translator.calculate("(-3-2)*9--+3");
        double expected = -42;
        Assert.assertEquals(expected, actual, 0.0);
    }
    @Test
    public void calcTest5() {
        ExpressionTranslator translator = new ExpressionTranslator();
        double actual = translator.calculate("-(-3-2)*9--+3");
        double expected = 48;
        Assert.assertEquals(expected, actual, 0.0);
    }

    @Test
    public void calcTest6() {
        ExpressionTranslator translator = new ExpressionTranslator();
        double actual = translator.calculate("-(-3-2)*9--+--3");
        double expected = 47;
        Assert.assertEquals(expected, actual, 0.0);
    }

}
