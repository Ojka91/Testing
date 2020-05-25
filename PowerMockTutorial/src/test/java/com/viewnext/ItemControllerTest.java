package com.viewnext;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.arrayContaining;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.anyVararg;
import static org.mockito.Mockito.times;
import static org.powermock.api.mockito.PowerMockito.doNothing;
import static org.powermock.api.mockito.PowerMockito.doCallRealMethod;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.verifyStatic;
import static org.powermock.api.mockito.PowerMockito.verifyPrivate;
import static org.powermock.api.mockito.PowerMockito.verifyNew;
import static org.powermock.api.mockito.PowerMockito.when;
import static org.powermock.api.mockito.PowerMockito.whenNew;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.tree.ExpandVetoException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.core.classloader.annotations.SuppressStaticInitializationFor;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import com.viewnext.ItemController;
import com.viewnext.ItemHelper;
import com.viewnext.SaludosHelper;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ ItemHelper.class, SaludosHelper.class, ItemController.class })
@SuppressStaticInitializationFor("com.viewnext.SaludosHelper")
public class ItemControllerTest {

	@Spy
	private ItemController itemController = new ItemController();

	@Before
	public void configurarTests() {
		mockStatic(ItemHelper.class, SaludosHelper.class);
	}

	/**
	 * M�todo para probar la generaci�n de un stub de un m�todo est�tico que
	 * devuelve un valor, haciendo que el mismo devuelva un array distinto al
	 * que por codificaci�n deber�a devolver.
	 * 
	 * Resultado esperado:
	 * 
	 * - Stub invocado. - Array de cadenas con los valores "one", "two",
	 * "three".
	 */
	@Test
	public void testObtenerItemsDevolviendoUnArrayDistintoAlCodificado() {
		// ARRANGE
		String[] resultado = new String[] {"one", "two", "three"};
		PowerMockito.when(ItemHelper.obtenerItems()).thenReturn(resultado);
		
		// ACT
		String[] array = ItemController.obtenerItems();
		
		// ASSERT
		verifyStatic(times(1));
		ItemHelper.obtenerItems();
		
		assertThat(array, arrayContaining(resultado));
	}

	/**
	 * M�todo para probar la generaci�n de un stub de un m�todo est�tico que no
	 * retorna valor, haciendo que el mismo no se ejecute.
	 * 
	 * Resultado esperado:
	 * 
	 * - Stub invocado. - No se lanza excepci�n alguna.
	 */
	@Test
	public void testImprimirItemsEvitandoLanzamientoExcepcion() {
//		doNothing().when(ItemHelper.class);
//		ItemHelper.imprimirItems();
		
		try {
			ItemController.imprimirItems();
		}catch (Exception e) {
			fail("No se esperaba excepci�n");
		}
		
		verifyStatic();
		ItemHelper.imprimirItems();
	}

	/**
	 * M�todo para probar el mockeo de una clase con un bloque est�tico.
	 * 
	 * Resultado esperado:
	 * 
	 * - Stub invocado. - No se lanza excepci�n alguna.
	 */
	@Test
	public void testSaludarEvitandoLanzamientoExcepcion() {
		doCallRealMethod().when(SaludosHelper.class);
		SaludosHelper.saludar();
		
		ItemController.saludar();
		
		verifyStatic();
		SaludosHelper.saludar();
	}

	/**
	 * M�todo para probar la generaci�n de un stub de un m�todo privado que
	 * retorna valor, haciendo que el mismo devuelva un valor distinto al que
	 * por codificaci�n deber�a devolver.
	 * 
	 * Resultado esperado:
	 * 
	 * - Stub invocado. - No se lanza excepci�n alguna.
	 */
	@Test
	public void testRealizarValidacionSinLanzamientoExcepcion() {
		try {
			when(itemController, "obtenerEstadoValidacion").thenReturn(Boolean.TRUE);
		} catch (Exception e) {
			fail("No se esperaban Excepciones");
		}
		
		itemController.realizarValidacion();
		
		try {
			verifyPrivate(itemController).invoke("obtenerEstadoValidacion");
		} catch (Exception e) {
			fail("No se esperaban Excepciones en la validaci�n");
		}
	}

	/**
	 * M�todo para probar la modificaci�n del valor de un atributo de instancia
	 * de forma que podamos guiar el flujo de ejecuci�n.
	 * 
	 * Resultado esperado:
	 * 
	 * - No se lanza excepci�n alguna.
	 */
	@Test
	public void testRealizarValidacionSinLanzamientoExcepcionModificandoInstancia() {
		Whitebox.setInternalState(itemController, "estadoValidacion", Boolean.TRUE);
		
		itemController.realizarValidacion();
	}

	/**
	 * M�todo para probar la modificaci�n de una instanciaci�n de forma que se
	 * tenga control sobre qu� debe generar la misma.
	 * 
	 * Resultado esperado:
	 * 
	 * - Instanciaci�n ejecutada. - Valor instanciado es el esperado. - No se
	 * lanza excepci�n alguna.
	 */
	@Test
	public void testMockeandoInstanciacion() {
		SimpleDateFormat sdf = new SimpleDateFormat(ItemController.PATRON_FECHA);
		String fechaString = "02/02/2020 02:02:02";
		Date fecha = null;
		try{
			fecha = sdf.parse(fechaString);
		}catch (Exception e) {
			fail("Error parseando fecha");
		}
		
		// ARRANGE
		try {
			whenNew(Date.class).withNoArguments().thenReturn(fecha);
		} catch (Exception e) {
			fail("Error excepci�n no esperada");
		}
		
		// ACT
		String resultado = itemController.obtenerFechaComoCadena();
		// ASSERT
		try {
			verifyNew(Date.class).withNoArguments();
		} catch (Exception e) {
			fail("Error excepci�n no esperada");
		}
		
		assertThat(resultado, equalTo(fechaString));
	}

}
