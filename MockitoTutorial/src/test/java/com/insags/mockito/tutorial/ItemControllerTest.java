package com.insags.mockito.tutorial;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;

import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import com.insags.mockito.tutorial.impl.ItemControllerImpl;

@RunWith(MockitoJUnitRunner.class)
public class ItemControllerTest {

	@Mock
	private ItemDao itemDaoMock;

	@InjectMocks
	private ItemController itemController = new ItemControllerImpl();

	/**
	 * M�todo para probar que la obtenci�n de todos los items se realiza de
	 * forma exitosa.
	 * 
	 * Resultado esperado:
	 * 
	 * - Se invoca el stub generado. - Se ha recuperado un array con Peonza y
	 * Mu�eca como elementos contenidos.
	 */
	@Test
	public void comprobarObtencionExitosaDeTodosLosItems() {
		// ARRANGE
		Mockito.when(itemDaoMock.obtenerTodosLosItems()).thenReturn(new String[] {"Peonza", "Mu�eca"});
		// ACT
		List<String> resultado = itemController.obtenerTodosLosItems();
		// ASSERT
		Mockito.verify(itemDaoMock, times(1)).obtenerTodosLosItems();
		assertThat(resultado, allOf(notNullValue(), contains("Peonza", "Mu�eca")));
	}

	/**
	 * M�todo para probar que la obtenci�n de todos los items se realiza de
	 * forma exitosa, pero haciendo uso de un objeto Answer.
	 * 
	 * Resultado esperado:
	 * 
	 * - Se invoca el stub generado. - Se ha recuperado un array con Peonza y
	 * Mu�eca como elementos contenidos.
	 */
	@Test
	public void comprobarObtencionExitosaDeTodosLosItemsHaciendoUsoDeAnswer() {
		// ARRANGE
		Answer<String[]> answer = new Answer<String[]>() {
			
			@Override
			public String[] answer(InvocationOnMock invocation) throws Throwable {
				return new String[] {"Peonza", "Mu�eca"};
			}
		};
		
		Mockito.when(itemDaoMock.obtenerTodosLosItems()).then(answer);
		// ACT
		List<String> resultado = null;
		try{
			resultado = itemController.obtenerTodosLosItems();
		} catch (Exception e) {
			fail("Error ejecutando el m�todo");
		}
		// ASSERT
		Mockito.verify(itemDaoMock, times(1)).obtenerTodosLosItems();
		assertThat(resultado, allOf(notNullValue(), contains("Peonza", "Mu�eca")));
	}

	/**
	 * M�todo para probar el caso en el que la obtenci�n de items no devuelve
	 * contenido, s�lo la colecci�n instanciada.
	 * 
	 * Resultado esperado:
	 * 
	 * - Se invoca el stub generado. - Se ha recuperado una colecci�n vac�a.
	 */
	@Test
	public void comprobarObtencionVaciaDeTodosLosItems() {
		// ARRANGE
		Mockito.when(itemDaoMock.obtenerTodosLosItems()).thenReturn(new String[] {});
		
		// ACT
		List<String> resultado = null;
		try{
			resultado = itemController.obtenerTodosLosItems();
		} catch (Exception e) {
			fail("Error ejecutando el m�todo");
		}
		
		// ASSERT
		assertThat(resultado, notNullValue());
		assertThat(resultado, empty());
		
		assertThat(resultado, allOf(notNullValue(), empty()));
		
		Mockito.verify(itemDaoMock, times(1)).obtenerTodosLosItems();
	}

	/**
	 * M�todo para probar el caso en el que la obtenci�n de items genera una
	 * excepci�n de tipo NullPointerException. El tipo de la excepci�n es
	 * indicado como par�metro de la anotaci�n @Test
	 * 
	 * Resultado esperado:
	 * 
	 * - Se invoca el stub generado. - Se ha lanzado la excepci�n de tipo
	 * NullPointerException.
	 */
	@Test(expected = NullPointerException.class)
	public void comprobarPorAnotacionLanzamientoExcepcionPorFaltaInyeccionDaoControlandoPorNotacion() throws Exception {
		// ARRANGE
		Mockito.when(itemDaoMock.obtenerTodosLosItems()).thenReturn(null);
		// ACT
		List<String> resultado = null;
		try {
			resultado = itemController.obtenerTodosLosItems();
		} catch (Exception e) {
			Mockito.verify(itemDaoMock, times(1)).obtenerTodosLosItems();
			
			throw e;
		}
	}

	/**
	 * M�todo para probar el caso en el que la obtenci�n de items genera una
	 * excepci�n de tipo NullPointerException. La prueba no parametriza la
	 * anotaci�n @Test
	 * 
	 * Resultado esperado:
	 * 
	 * - Se invoca el stub generado. - Se ha lanzado la excepci�n de tipo
	 * NullPointerException.
	 */
	@Test
	public void comprobarObtencionItemsErroneaPorLanzamientoExcepcionPorFaltaInyeccionDaoControlandoPorCodigo() {
		// ARRANGE
		Mockito.when(itemDaoMock.obtenerTodosLosItems()).thenThrow(NullPointerException.class);
		//ACT
		
		try {
			itemController.obtenerTodosLosItems();
		}catch (Exception e) {
			//ASSERT
			Mockito.verify(itemDaoMock).obtenerTodosLosItems();
			assertThat(e, instanceOf(NullPointerException.class));
		}
		
		/*
		try {
			itemController.obtenerTodosLosItems();
		}catch (NullPointerException e) {
			//ASSERT
			Mockito.verify(itemDaoMock).obtenerTodosLosItems();
		} catch(Exception ex) {
			fail("La excepci�n no es de tipo nullpointerException");
		}
		*/
	}
	
	@Rule
    public ExpectedException thrown= ExpectedException.none();
	
	@Test
	public void comprobarPorAnotacionLanzamientoExcepcionPorFaltaInyeccionDaoControlandoPorNotacion_2() throws Exception {
		// ARRANGE
		Mockito.when(itemDaoMock.obtenerTodosLosItems()).thenReturn(null);
		thrown.expect(NullPointerException.class);
		
		// ACT
		List<String> resultado = null;
		resultado = itemController.obtenerTodosLosItems();
	}
	

	/**
	 * M�todo para probar el caso en el que la actualizaci�n de un item se lleva
	 * a cabo de forma exitosa.
	 * 
	 * Resultado esperado:
	 * 
	 * - Se invoca el stub generado. - No se ha lanzado excepci�n alguna.
	 */
	@Test
	public void comprobarActualizacionExitosaDeItem() {
		Mockito.doNothing().when(itemDaoMock).actualizarItem(anyString(), anyInt());
		
		itemController.actualizarItem("asdasd", 1);
		
		Mockito.verify(itemDaoMock).actualizarItem("asdasd", 1);	
	}

	/**
	 * M�todo para probar el caso en el que la actualizaci�n de un item provoca
	 * una excepci�n de tipo ArrayIndexOutOfBoundsException, la cual tiene
	 * asociada el mensaje "Excepci�n esperada". No se hace uso de la
	 * parametrizaci�n de la anotaci�n @Test
	 * 
	 * Resultado esperado:
	 * 
	 * - Se invoca el stub generado. - Se ha lanzado una excepci�n de tipo
	 * ArrayIndexOutOfBoundsException. - El mensaje de la excepci�n es
	 * "Excepci�n esperada".
	 */
	@Test
	public void comprobarActualizacionFallidaPorExcederTama�oDelArray() {
		Mockito.doThrow(new ArrayIndexOutOfBoundsException("Excepci�n esperada")).when(itemDaoMock).actualizarItem(anyString(), anyInt());
		
		try {
			itemController.actualizarItem("wew", 3);
		} catch (ArrayIndexOutOfBoundsException e) {
			Mockito.verify(itemDaoMock).actualizarItem("wew", 3);
			assertThat(e.getMessage(), is("Excepci�n esperada"));
		} catch(Exception ex) {
			fail("La excdepci�n no es de tipo ArrayIndexOutOfBoundsException");
		}
	}

	/**
	 * M�todo para probar el caso en el que se realiza una actualizaci�n exitosa
	 * de un item, mostrando adem�s por consola la cadena proporcionada, a la
	 * que se le han eliminado todos los espacios en blanco.
	 * 
	 * Resultado esperado:
	 * 
	 * - Se invoca el stub generado. - Se ha mostrado por consola la cadena
	 * "UnoDosTresCuatroCinco".
	 */
	@Test
	public void comprobarActualizacionExitosaDeItemMostrandoEnConsolaSuValorEliminandoEspaciosEnBlancoUsandoAnswer() {
		String item = "Uno Dos  Tres Cuatro     Cinco";
		
		Answer<String> answer = new Answer<String>() {
			
			@Override
			public String answer(InvocationOnMock invocation) throws Throwable {
				String argIn = invocation.getArgumentAt(0, String.class);
				System.out.println(argIn.replace(" ", ""));
				return argIn;
			}
		};
			
		Mockito.doAnswer(answer).when(itemDaoMock).actualizarItem(anyString(), anyInt());
		
		itemController.actualizarItem(item, 2);
		
		Mockito.verify(itemDaoMock).actualizarItem(item, 2);
	}

	/**
	 * M�todo para probar el caso en el que el reseteo de items se lleva a cabo
	 * de forma satisfactoria.
	 * 
	 * Resultado esperado:
	 * 
	 * - Se invoca el stub generado. - No se ha lanzado excepci�n alguna. -
	 * Comprobar que el n�mero de actualizaciones llevadas a cabo son 2.
	 */
	@Test
	public void comprobarReseteoExitosoDeItemsVerificandoNumeroEstrictoDeReseteos() {
		Mockito.doNothing().when(itemDaoMock).actualizarItem(anyString(), anyInt());
		
		Integer[] posiciones = {1,2};
		try {
			itemController.resetearItemsEspecificos(posiciones);
		} catch (Exception e) {
			fail("No se esperaba ninguna excepci�n");
		}
		
		Mockito.verify(itemDaoMock, times(2)).actualizarItem(anyString(), anyInt());
	}

	/**
	 * M�todo para probar el caso en el que el listado de items se lleva a cabo
	 * de forma exitosa.
	 * 
	 * Resultado esperado:
	 * 
	 * - Se invoca el stub generado. - No se ha lanzado excepci�n alguna.
	 */
	@Test
	public void comprobarListadoItemsParaReseteoConControlEstrictoIvocaciones() {
		Mockito.doNothing().when(itemDaoMock).actualizarItem(anyString(), anyInt());
		Mockito.when(itemDaoMock.obtenerTodosLosItems()).thenReturn(new String[] {"uno", "dos", "tres"});
		
		try {
			itemController.listaItemsParaReseteo();
		}catch(Exception e) {
			fail("No se esperaba ninguna excepci�n");
		}
		
		Mockito.verify(itemDaoMock, times(3)).actualizarItem(anyString(), anyInt());
	}

}
