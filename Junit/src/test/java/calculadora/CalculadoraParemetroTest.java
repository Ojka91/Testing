package calculadora;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 *
 * You can try a lot of different cases for each method
 * using this annotations saving a lot of code writting
 * manually each example.
 * This case try method Add
 *
 */
@RunWith(value = Parameterized.class)
public class CalculadoraParemetroTest {


    @Parameterized.Parameters
    public static Iterable<Object[]> getData(){
        return Arrays.asList(new Object[][]{
                {3,1,4}, {2,3,5}, {3,3,6}
        });
        //same thing,short write

       // List<Object[]> obj = new ArrayList<Object[]>();
        //obj.add(new Object[] {3, 1, 4});
        //obj.add(new Object[]{2,3,5});
        //obj.add(new Object[]{3,3,6});
        //return obj;
    }

    private int a, b, exp;

    public CalculadoraParemetroTest (int a, int b, int exp){
        this.a = a;
        this.b = b;
        this.exp = exp;
    }

    @Test
    public void testAdd(){
        Calculadora calc = new Calculadora();
        int result = calc.add(a, b);
        assertEquals( exp , result);
    }
}
