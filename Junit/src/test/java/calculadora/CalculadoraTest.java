package calculadora;


import org.junit.*;

import static org.junit.Assert.*;

/**
 *
 * @Test test methods
 * @Before try each time before method
 * @After try each time after method
 * @AfterClass - @BeforeClass only once before and after everything
 * @Test(value = AritmethicException) expect that result
 * @Test(timeout = x) delay on method
 *
 */

public class CalculadoraTest {

   static Calculadora calc;

    @BeforeClass
    public static void beforeClass(){
        System.out.println("beforeClass()");
        calc = new Calculadora();

    }

    @AfterClass
    public static void afterClass(){
        System.out.println("afterClass()");
    }


    @Before //each time before test
    public void before(){
        System.out.println("before()");
        calc.clear();
    }


    @After //each time after test
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
