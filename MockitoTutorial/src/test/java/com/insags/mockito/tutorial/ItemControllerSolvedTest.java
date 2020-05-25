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
	 * Método para probar que la obtención de todos los items se realiza de
	 * forma exitosa.
	 * 
	 * Resultado esperado:
	 * 
	 * - Se invoca el stub generado. - Se ha recuperado un array con Peonza y
	 * Muñeca como elementos contenidos.
	 */
	@Test
	public void comprobarObtencionExitosaDeTodosLosItems() {
		// ARRANGE
		List<String> retornoObtenido = null;
		Mockito.when(itemDaoMock.obtenerTodosLosItems()).thenReturn(new String[] { "Peonza", "Muñeca" });
		// ACT
		try {
			retornoObtenido = itemController.obtenerTodosLosItems();
		} catch (Exception ex) {
			fail("Se ha lanzado una excepción no esperada.\n");
		}
		// ASSERT
		Mockito.verify(itemDaoMock).obtenerTodosLosItems();
		assertThat(retornoObtenido, allOf(is(notNullValue()), contains("Peonza", "Muñeca")));
	}

	/**
	 * Método para probar que la obtención de todos los items se realiza de
	 * forma exitosa, pero haciendo uso de un objeto Answer.
	 * 
	 * Resultado esperado:
	 * 
	 * - Se invoca el stub generado. - Se ha recuperado un array con Peonza y
	 * Muñeca como elementos contenidos.
	 */
	@Test
	public void comprobarObtencionExitosaDeTodosLosItemsHaciendoUsoDeAnswer() {
		// ARRANGE
		List<String> retornoObtenido = null;
		Answer<String[]> respuesta = new Answer<String[]>() {
			@Override
			public String[] answer(InvocationOnMock invocation) throws Throwable {
				return new String[] { "Peonza", "Muñeca" };
			}
		};
		Mockito.when(itemDaoMock.obtenerTodosLosItems()).then(respuesta);
		// ACT
		try {
			retornoObtenido = itemController.obtenerTodosLosItems();
		} catch (Exception ex) {
			fail("Se ha lanzado una excepción no esperada.\n");
		}
		// ASSERT
		Mockito.verify(itemDaoMock).obtenerTodosLosItems();
		assertThat(retornoObtenido, allOf(is(notNullValue()), contains("Peonza", "Muñeca")));
	}

	/**
	 * Método para probar el caso en el que la obtención de items no devuelve
	 * contenido, sólo la colección instanciada.
	 * 
	 * Resultado esperado:
	 * 
	 * - Se invoca el stub generado. - Se ha recuperado una colección vacía.
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
			fail("Se ha lanzado una excepción no esperada.\n");
		}
		// ASSERT
		Mockito.verify(itemDaoMock).obtenerTodosLosItems();
		assertThat(retornoObtenido, allOf(is(notNullValue()), is(empty())));
	}

	/**
	 * Método para probar el caso en el que la obtención de items genera una
	 * excepción de tipo NullPointerException. El tipo de la excepción es
	 * indicado como parámetro de la anotación @Test
	 * 
	 * Resultado esperado:
	 * 
	 * - Se invoca el stub generado. - Se ha lanzado la excepción de tipo
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
		fail("Se esperaba el lanzamiento de una excepción no esperada.\n");
	}

	/**
	 * Método para probar el caso en el que la obtención de items genera una
	 * excepción de tipo NullPointerException. La prueba no parametriza la
	 * anotación @Test
	 * 
	 * Resultado esperado:
	 * 
	 * - Se invoca el stub generado. - Se ha lanzado la excepción de tipo
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
			fail("Se ha lanzado una excepción no esperada.\n");
		}
	}

	/**
	 * Método para probar el caso en el que la actualización de un item se lleva
	 * a cabo de forma exitosa.
	 * 
	 * Resultado esperado:
	 * 
	 * - Se invoca el stub generado. - No se ha lanzado excepción alguna.
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
			fail("Se ha lanzado una excepción no esperada.\n");
		}
		// ASSERT
		Mockito.verify(itemDaoMock).actualizarItem(item, posicion);
	}

	/**
	 * Método para probar el caso en el que la actualización de un item provoca
	 * una excepción de tipo ArrayIndexOutOfBoundsException, la cual tiene
	 * asociada el mensaje "Excepción esperada". No se hace uso de la
	 * parametrización de la anotación @Test
	 * 
	 * Resultado esperado:
	 * 
	 * - Se invoca el stub generado. - Se ha lanzado una excepción de tipo
	 * ArrayIndexOutOfBoundsException. - El mensaje de la excepción es
	 * "Excepción esperada".
	 */
	@Test
	public void comprobarActualizacionFallidaPorExcederTamañoDelArray() {
		// ARRANGE
		String item = "Arco";
		Integer posicion = 10;
		ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException = new ArrayIndexOutOfBoundsException(
				"Excepción esperada");
		Mockito.doThrow(arrayIndexOutOfBoundsException).when(itemDaoMock).actualizarItem(item, posicion);
		// ACT
		try {
			this.itemController.actualizarItem(item, posicion);
		} catch (ArrayIndexOutOfBoundsException ex) {
			// ASSERT
			Mockito.verify(itemDaoMock).actualizarItem(item, posicion);
			assertThat(ex.getMessage(), is("Excepción esperada"));
		} catch (Exception ex) {
			fail("Se ha lanzado una excepción no esperada.\n");
		}
	}

	/**
	 * Método para probar el caso en el que se realiza una actualización exitosa
	 * de un item, mostrando además por consola la cadena proporcionada, a la
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
			fail("Se ha lanzado una excepción no esperada.\n");
		}
		// ASSERT
		Mockito.verify(itemDaoMock).actualizarItem(item, posicion);
	}

	/**
	 * Método para probar el caso en el que el reseteo de items se lleva a cabo
	 * de forma satisfactoria.
	 * 
	 * Resultado esperado:
	 * 
	 * - Se invoca el stub generado. - No se ha lanzado excepción alguna. -
	 * Comprobar que el número de actualizaciones llevas a cabo son 2.
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
			fail("Se ha lanzado una excepción no esperada.\n");
		}
		// ASSERT
		Mockito.verify(itemDaoMock, Mockito.times(2)).actualizarItem(Mockito.eq(EMPTY_STRING), Matchers.anyInt());
	}

	/**
	 * Método para probar el caso en el que el listado de items se lleva a cabo
	 * de forma exitosa.
	 * 
	 * Resultado esperado:
	 * 
	 * - Se invoca el stub generado. - No se ha lanzado excepción alguna.
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
			fail("Se ha lanzado una excepción no esperada.\n");
		}
		// ASSERT
		InOrder inOrder = Mockito.inOrder(itemDaoMock);
		inOrder.verify(itemDaoMock).obtenerTodosLosItems();
		inOrder.verify(itemDaoMock, Mockito.times(3)).actualizarItem(Matchers.any(String.class), Matchers.anyInt());
	}

}
