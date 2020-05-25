package com.insags.framework.test.base;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * Base test del proyecto para los test de integración.
 * @author INSA.
 * -javaagent:C:/Entorno/LibreriasTest/JunitINSA/powermock-module-javaagent-1.6.1.jar
 */

@ContextConfiguration(locations = { "/spring/web.application.context.test.integracion.xml" })
@TransactionConfiguration(transactionManager = "txManager")
@Transactional
@WebAppConfiguration
public class BaseTestIntegracion {
		
}











