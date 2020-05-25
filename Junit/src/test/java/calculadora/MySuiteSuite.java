package calculadora;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 *
 * Can choose inside @Suite.Suiteclasses
 * which classes you want to test
 *
 *
 */

@RunWith(value = Suite.class)
@Suite.SuiteClasses({
        CalculadoraTest.class,
        CalculadoraParemetroTest.class
})
public class MySuiteSuite {
}
