package com.insags.framework.test.util;

import org.springframework.aop.framework.Advised;
import org.springframework.aop.support.AopUtils;
import org.springframework.test.util.ReflectionTestUtils;

/**
 * Clase de utilería para los test unitarios.
 * @author INSA
 */
public final class TestUtil {
	
	/**
	 * Constructor por defecto.<br>
	 */
	private TestUtil() {
		
	}
	
	/**
     * Método para inyectar un bean a una clase
     * 
     * @param nombrePropiedadInyectar
     *            nombre de la propiedad a inyectar
     * @param instanciaALaQueSeInyecta
     *            instancia a la que se inyecta
     * @param beanAInyectar
     *            el bean a inyectar
     * @throws Exception 
     */
    public static void inyectarBean(String nombrePropiedadInyectar, Object instanciaALaQueSeInyecta,
            Object beanAInyectar) throws Exception {
        ReflectionTestUtils.setField(unwrapService(instanciaALaQueSeInyecta), nombrePropiedadInyectar, beanAInyectar);
    }   

    /**
     * Método para obtener la clase real en lugar del Proxy que genera Spring, para poder inyectar
     * los valores de los mocks.<br>
     * @param bean El bean, ya sea un Proxy o un Bean normal.
     * @return El bean.
     * @throws Exception En caso de que se produzca excepción.
     */
    private static Object unwrapService(Object bean) throws Exception {

        if (AopUtils.isAopProxy(bean) && bean instanceof Advised) {
            Object target = ((Advised) bean).getTargetSource().getTarget();
            return target;
        }
        return bean;
    }
}
