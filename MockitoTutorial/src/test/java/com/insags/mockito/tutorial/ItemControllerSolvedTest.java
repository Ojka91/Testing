package com.insags.mockito.tutorial;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import com.insags.mockito.tutorial.impl.ItemControllerImpl;

@RunWith(MockitoJUnitRunner.class)
public class ItemControllerSolvedTest {

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
		List<String> retornoObtenido = null;
		Mockito.when(itemDaoMock.obtenerTodosLosItems()).thenReturn(new String[] { "Peonza", "Mu�eca" });
		// ACT
		try {
			retornoObtenido = itemController.obtenerTodosLosItems();
		} catch (Exception ex) {
			fail("Se ha lanzado una excepci�n no esperada.\n");
		}
		// ASSERT
		Mockito.verify(itemDaoMock).obtenerTodosLosItems();
		assertThat(retornoObtenido, allOf(is(notNullValue()), contains("Peonza", "Mu�eca")));
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
		List<String> retornoObtenido = null;
		Answer<String[]> respuesta = new Answer<String[]>() {
			@Override
			public String[] answer(InvocationOnMock invocation) throws Throwable {
				return new String[] { "Peonza", "Mu�eca" };
			}
		};
		Mockito.when(itemDaoMock.obtenerTodosLosItems()).then(respuesta);
		// ACT
		try {
			retornoObtenido = itemController.obtenerTodosLosItems();
		} catch (Exception ex) {
			fail("Se ha lanzado una excepci�n no esperada.\n");
		}
		// ASSERT
		Mockito.verify(itemDaoMock).obtenerTodosLosItems();
		assertThat(retornoObtenido, allOf(is(notNullValue()), contains("Peonza", "Mu�eca")));
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
		List<String> retornoObtenido = null;
		Mockito.when(itemDaoMock.obtenerTodosLosItems()).thenReturn(new String[] {});
		// ACT
		try {
			retornoObtenido = itemController.obtenerTodosLosItems();
		} catch (Exception ex) {
			fail("Se ha lanzado una excepci�n no esperada.\n");
		}
		// ASSERT
		Mockito.verify(itemDaoMock).obtenerTodosLosItems();
		assertThat(retornoObtenido, allOf(is(notNullValue()), is(empty())));
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
	// FIXME
	public void comprobarPorAnotacionLanzamientoExcepcionPorFaltaInyeccionDaoControlandoPorNotacion() throws Exception {
		// ARRANGE
		Mockito.when(itemDaoMock.obtenerTodosLosItems()).thenThrow(NullPointerException.class);
		// ACT
		try {
			itemController.obtenerTodosLosItems();
		} catch (Exception ex) {
			// ASSERT
			Mockito.verify(itemDaoMock).obtenerTodosLosItems();
			throw ex;
		}
		fail("Se esperaba el lanzamiento de una excepci�n no esperada.\n");
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
		// ACT
		try {
			itemController.obtenerTodosLosItems();
		} catch (NullPointerException ex) {
			// ASSERT
			Mockito.verify(itemDaoMock).obtenerTodosLosItems();
		} catch (Exception ex) {
			fail("Se ha lanzado una excepci�n no esperada.\n");
		}
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
		// ARRANGE
		String item = "Arco";
		Integer posicion = 1;
		Mockito.doNothing().when(itemDaoMock).actualizarItem(item, posicion);
		// ACT
		try {
			this.itemController.actualizarItem(item, posicion);
		} catch (Exception ex) {
			fail("Se ha lanzado una excepci�n no esperada.\n");
		}
		// ASSERT
		Mockito.verify(itemDaoMock).actualizarItem(item, posicion);
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
		// ARRANGE
		String item = "Arco";
		Integer posicion = 10;
		ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException = new ArrayIndexOutOfBoundsException(
				"Excepci�n esperada");
		Mockito.doThrow(arrayIndexOutOfBoundsException).when(itemDaoMock).actualizarItem(item, posicion);
		// ACT
		try {
			this.itemController.actualizarItem(item, posicion);
		} catch (ArrayIndexOutOfBoundsException ex) {
			// ASSERT
			Mockito.verify(itemDaoMock).actualizarItem(item, posicion);
			assertThat(ex.getMessage(), is("Excepci�n esperada"));
		} catch (Exception ex) {
			fail("Se ha lanzado una excepci�n no esperada.\n");
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
		// ARRANGE
		String item = " Uno Dos Tres Cuatro Cinco ";
		Integer posicion = 1;
		Answer<String> respuesta = new Answer<String>() {
			@Override
			public String answer(InvocationOnMock invocation) throws Throwable {
				String item = invocation.getArgumentAt(0, String.class);
				System.out.println(item.replaceAll("\\s", ""));
				return item;
			}
		};
		Mockito.doAnswer(respuesta).when(itemDaoMock).actualizarItem(item, posicion);
		// ACT
		try {
			this.itemController.actualizarItem(item, posicion);
		} catch (Exception ex) {
			fail("Se ha lanzado una excepci�n no esperada.\n");
		}
		// ASSERT
		Mockito.verify(itemDaoMock).actualizarItem(item, posicion);
	}

	/**
	 * M�todo para probar el caso en el que el reseteo de items se lleva a cabo
	 * de forma satisfactoria.
	 * 
	 * Resultado esperado:
	 * 
	 * - Se invoca el stub generado. - No se ha lanzado excepci�n alguna. -
	 * Comprobar que el n�mero de actualizaciones llevas a cabo son 2.
	 */
	@Test
	public void comprobarReseteoExitosoDeItemsVerificandoNumeroEstrictoDeReseteos() {
		// ARRANGE
		String EMPTY_STRING = "";
		Integer[] posiciones = { 2, 3 };
		// ACT
		try {
			itemController.resetearItemsEspecificos(posiciones);
		} catch (Exception ex) {
			fail("Se ha lanzado una excepci�n no esperada.\n");
		}
		// ASSERT
		Mockito.verify(itemDaoMock, Mockito.times(2)).actualizarItem(Mockito.eq(EMPTY_STRING), Matchers.anyInt());
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
		// ARRANGE
		String[] items = { "uno", "dos", "tres" };
		Mockito.when(itemDaoMock.obtenerTodosLosItems()).thenReturn(items);
		// ACT
		try {
			itemController.listaItemsParaReseteo();
		} catch (Exception ex) {
			fail("Se ha lanzado una excepci�n no esperada.\n");
		}
		// ASSERT
		InOrder inOrder = Mockito.inOrder(itemDaoMock);
		inOrder.verify(itemDaoMock).obtenerTodosLosItems();
		inOrder.verify(itemDaoMock, Mockito.times(3)).actualizarItem(Matchers.any(String.class), Matchers.anyInt());
	}

}
