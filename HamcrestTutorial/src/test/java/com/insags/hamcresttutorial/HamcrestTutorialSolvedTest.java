package com.insags.hamcresttutorial;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.any;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.arrayContaining;
import static org.hamcrest.Matchers.arrayContainingInAnyOrder;
import static org.hamcrest.Matchers.arrayWithSize;
import static org.hamcrest.Matchers.both;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.either;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.emptyArray;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItemInArray;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.startsWith;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

public class HamcrestTutorialSolvedTest {

	/**
	 * Test que realiza la comprobación del valor obtenido con respecto al
	 * esperado de cuatro formas distintas.
	 */
	@Test
	public void comprobarDeCuatroFormasCadenasIguales() {
		String expected = "Expected";
		String actual = "Expected";
		assertEquals(expected, actual);
		assertThat(actual, equalTo(expected));
		assertThat(actual, is(equalTo(expected)));
		assertThat(actual, is(expected));
	}

	/**
	 * Test para comprobar que el número proporcionado es menor que con el que
	 * se compara.
	 */
	@Test
	public void comprobarNumeroProporcionadoMenorQueValorDado() {
		Integer numeroProporcionado = 7;
		Integer valorDado = 10;
		assertThat(numeroProporcionado, is(lessThan(valorDado)));
	}

	/**
	 * Test para comprobar que el número proporcionado es menor que con el que
	 * se compara.
	 */
	@Test
	public void comprobarNumeroProporcionadoMayorQueValorDado() {
		Integer numeroProporcionado = 7;
		Integer valorDado = 5;
		assertThat(numeroProporcionado, is(greaterThan(valorDado)));
	}

	/**
	 * Test para comprobar que una cadena lo es sin hacer uso de 'instanceof'.
	 */
	@Test
	public void comprobarQueEsUnaCadenaSinImportarValorContenido() {
		String cualquierCadena = "Cualquiera";
		assertThat(cualquierCadena, is(any(String.class)));
	}

	/**
	 * Test para comprobar dos tipos distintos con el mismo conjunto de
	 * Matchers.
	 */
	@Test
	public void comprobarDosTiposDistintosConMismoConjuntoMatchers() {
		String cualquierCadena = "Cualquiera";
		Integer cualquierNumero = 4;
		assertThat(cualquierCadena, is(anything()));
		assertThat(cualquierNumero, is(anything()));
	}

	/**
	 * Test para la comprobación estricta del orden de los elementos de un
	 * array.
	 */
	@Test
	public void comprobarContenidoArrayConEstrictezEnOrden() {
		String[] cadenas = { "uno", "dos", "tres" };
		assertThat(cadenas, is(arrayContaining("uno", "dos", "tres")));
	}

	/**
	 * Test para la comprobación laxa del orden de los elementos de un array.
	 */
	@Test
	public void comprobarContenidoArrayConLaxitudEnOrden() {
		String[] cadenas = { "uno", "dos", "tres" };
		assertThat(cadenas, is(arrayContainingInAnyOrder("tres", "dos", "uno")));
	}

	/**
	 * Test para la comprobación estricta de orden de los elementos de un array
	 * donde: <br/>
	 * - El primer elemento comience por "un". <br/>
	 * - El segundo elemento sea igual a "dos". <br/>
	 * - El tercer elemento termine por "es".
	 */
	@Test
	public void comprobarRestriccionesContenidoArrayConEstrictez() {
		String[] cadenas = { "uno", "dos", "tres" };
		assertThat(
				cadenas,
				is(arrayContaining(startsWith("un"), equalTo("dos"),
						endsWith("es"))));
	}

	/**
	 * Test para la comprobación laxa del orden de los elementos de un array
	 * donde: <br/>
	 * - Un elemento comience por "un". <br/>
	 * - Un elemento comience sea igual a "dos". <br/>
	 * - Un elemento termine por "es".
	 */
	@Test
	public void comprobarRestriccionesContenidoArrayConLaxitud() {
		String[] cadenas = { "dos", "tres", "uno" };
		assertThat(
				cadenas,
				is(arrayContainingInAnyOrder(endsWith("es"), startsWith("un"),
						equalTo("dos"))));
	}

	/**
	 * Test para comprobar el tamaño de un array.
	 */
	@Test
	public void comprobarTamañoArray() {
		String[] cadenas = { "uno", "dos", "tres" };
		Integer tamanyo = 3;
		assertThat(cadenas, is(arrayWithSize(tamanyo)));
	}

	/**
	 * Test para comprobar que el tamaño de un array es menor que un tamaño
	 * dado.
	 */
	@Test
	public void comprobarTamañoArrayMenorQueTamanyoDado() {
		String[] cadenas = { "uno", "dos", "tres" };
		Integer tamanyoDado = 4;
		assertThat(cadenas, is(arrayWithSize(lessThan(tamanyoDado))));
	}

	/**
	 * Test para comprobar que el tamaño de un array es mayor que un tamaño
	 * dado.
	 */
	@Test
	public void comprobarTamañoArrayMayorQueTamanyoDado() {
		String[] cadenas = { "uno", "dos", "tres" };
		Integer tamanyoDado = 2;
		assertThat(cadenas, is(arrayWithSize(greaterThan(tamanyoDado))));
	}

	/**
	 * Test para comprobar que un número esta próximo a otro dentro de una
	 * cierta cantidad.
	 */
	@Test
	public void comprobarProximidadEntreNumeros() {
		Double valorReferencia = 6.8;
		Double valorComprobacion = 7.0;
		Double proximidad = 0.5;
		assertThat(valorReferencia, is(closeTo(valorComprobacion, proximidad)));
	}

	/**
	 * Test para la comprobación de forma estricta el orden de los elementos de
	 * una lista.
	 */
	@Test
	public void comprobarOrdenContenidoListaConEstrictez() throws Exception {
		List<String> strings = Arrays.asList("uno", "dos", "tres");
		assertThat(strings, contains("uno", "dos", "tres"));
	}

	/**
	 * Test para la comprobación estricta de orden de los elementos de una lista
	 * donde: <br/>
	 * - El primer elemento termine por "es". <br/>
	 * - El segundo elemento comience por "un". <br/>
	 * - El tercer elemento sea igual a "dos".
	 */
	@Test
	public void comprobarRestriccionesContenidoListaConEstrictez()
			throws Exception {
		List<String> cadenas = Arrays.asList("uno", "tres", "dos");
		assertThat(cadenas,
				is(contains(startsWith("un"), endsWith("es"), equalTo("dos"))));
	}

	/**
	 * Test para la comprobación de forma estricta el orden de los elementos de
	 * una lista.
	 */
	@Test
	public void comprobarContenidoListaConLaxitud() throws Exception {
		List<String> cadenas = Arrays.asList("uno", "dos", "tres");
		assertThat(cadenas, containsInAnyOrder("tres", "dos", "uno"));
	}

	/**
	 * Test para la comprobación laxa de orden de los elementos de una lista
	 * donde: <br/>
	 * - Un elemento termine por "es". <br/>
	 * - Un elemento comience por "un". <br/>
	 * - Un elemento sea igual a "dos".
	 */
	@Test
	public void comprobarRestriccionesContenidoListaConLaxitud()
			throws Exception {
		List<String> cadenas = Arrays.asList("uno", "tres", "dos");
		assertThat(
				cadenas,
				is(containsInAnyOrder(equalTo("dos"), startsWith("un"),
						endsWith("es"))));
	}

	/**
	 * Test para comprobar que una cadena contiene otra dada.
	 */
	@Test
	public void test_containsString() {
		String cadena = "Cadena";
		String subcadena = "ade";
		assertThat(cadena, containsString(subcadena));
	}

	/**
	 * Test para comprobar si un array no es null.
	 */
	@Test
	public void comprobarSiArrayNoEsNull() {
		String[] array = new String[0];
		assertThat(array, is(not(nullValue())));
	}

	/**
	 * Test para comrpobar si una colección no es null.
	 */
	@Test
	public void comprobarSiColeccionNoEsNull() {
		Set<String> coleccion = new HashSet<String>();
		assertThat(coleccion, is(not(nullValue())));
	}

	/**
	 * Test para comprobar si un array está vacío.
	 */
	@Test
	public void comprobarArrayVacio() {
		String[] array = new String[0];
		assertThat(array, is(emptyArray()));
	}

	/**
	 * Test para comrpobar si una colección está vacía.
	 */
	@Test
	public void comprobarColeccionVacia() {
		Set<String> coleccion = new HashSet<String>();
		assertThat(coleccion, is(empty()));
	}

	/**
	 * Test para comprobar que dos cadenas son iguales ignorando las
	 * mayúsculas/minúsculas.
	 */
	@Test
	public void comprobarCadenasIgnorandoMayusculasMinusculas() {
		String cadenaEsperada = "valor";
		String cadenaObtenida = "VaLoR";
		assertThat(cadenaObtenida, equalToIgnoringCase(cadenaEsperada));
	}

	/**
	 * Test para comprobar si un array contiene un valor concreto.
	 */
	@Test
	public void test_hasItemInArray() {
		Integer[] array = { 1, 2, 7, 5, 4, 8 };
		Integer valor = 4;
		assertThat(array, hasItemInArray(valor));
	}

	/**
	 * Test para comprobar si una lista contiene un valor mayor que uno dado.
	 */
	@Test
	public void comprobarListaContieneResticcionMayorQueValor() {
		List<Integer> lista = Arrays.asList(1, 2, 7, 5, 4, 8);
		Integer valor = 6;
		assertThat(lista, hasItem(is(greaterThan(valor))));
	}

	/**
	 * Test para comprobar si un mapa tiene una clave dada.
	 */
	@Test
	public void comprobarMapaContieneEntradaConcreta() {
		Integer clave = 1;
		String valor = "uno";
		Map<Integer, String> mapa = new HashMap<Integer, String>();
		mapa.put(clave, valor);
		assertThat(mapa, hasEntry(1, "uno"));
	}

	/**
	 * Test para comprobar, en una sola línea, que una cadena cumple con todas
	 * las siguientes restricciones: <br/>
	 * - Comienza por "En un lugar". <br/>
	 * - Contiene la cadena "cuyo nombre". <br/>
	 * - Termina en puntos suspensivos ("...").
	 */
	@Test
	public void comprobarMultiplesRestriccionesEnCadena() {
		String cadena = "En un lugar de La Mancha, de cuyo nombre no quiero acordarme...";
		assertThat(
				cadena,
				allOf(startsWith("En un lugar"), containsString("cuyo nombre"),
						endsWith("...")));
	}

	/**
	 * Test para comprobar, en una sola línea, que una cadena cumple con alguna
	 * de las siguientes restricciones: <br/>
	 * - Contiene la cadena "remember". <br/>
	 * - Contiene la cadena "vendetta".
	 */
	@Test
	public void comprobarAlgunaRestriccionEnCadena() {
		String cadena = "Remember, remember the fifth of November, the gunpowder treason and plot.";
		assertThat(cadena,
				anyOf(containsString("remember"), containsString("vendetta")));
	}

	/**
	 * Test para comprobar, en una sola línea, que una cadena cumple con todas
	 * las siguientes restricciones: <br/>
	 * - Comienza por "¡Hola". <br/>
	 * - Termina por "mundo!".
	 */
	@Test
	public void comprobarAmbasRestriccionesEnCadena() {
		String cadena = "¡Hola, mundo!";
		assertThat(cadena, both(startsWith("¡Hola")).and(endsWith("mundo!")));
	}

	/**
	 * Test para comprobar, en una sola línea, que una cadena cumple con alguna
	 * las siguientes restricciones: <br/>
	 * - Comienza por "¡Hola". <br/>
	 * - Termina por "mundo!".
	 */
	@Test
	public void comprobarSoloUnaRestriccionEnCadena() {
		String cadena = "¡Hola, mundo.";
		assertThat(cadena, either(startsWith("¡Hola")).or(endsWith("mundo!")));
	}
}
