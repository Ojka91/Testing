package calculadora;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class CalculadoraTest {

    Calculadora calc;

    @Before
    public void before(){
        System.out.println("before()");
        calc = new Calculadora();
    }


    @After
    public void after(){
        System.out.println("after()");
        calc.clear();
    }

    @Test
    public void testAdd(){
        System.out.println("sum()");
        int result = calc.add(3, 2);
        int expected = 5;

        assertEquals(expected, result);
    }

    @Test
    public void testAnsSum(){
        System.out.println("ansSum()");
        calc.add(3, 2);
        int result = calc.ans();
        int expected = 5;

        assertEquals(expected, result);
    }

    @Test
    public void testMultiply(){
        double result = calc.multiply(3.05, 8.98);
        double expected = 27;

        assertEquals(expected, result, 0.5);
    }


    @Test
    public void testDiv(){
        calc.div(5, 2);
    }


    @Test(expected = ArithmeticException.class)
    public void testDivZero(){
        calc.div(5, 0);
    }


    @Test(timeout = 250)
    public void testThisMethodNeedsToWorkFast(){
        calc.thisMethodNeedToWorkFast();
    }


}
