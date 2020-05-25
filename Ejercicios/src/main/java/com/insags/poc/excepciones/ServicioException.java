package com.insags.poc.excepciones;

/**
 * Clase de excepci�n de servicio.
 * @author INSA.
 *
 */
public class ServicioException extends Exception {

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 1885257278912322245L;

	/**
	 * Constructor
	 * @param mensaje El mensaje.
	 */
	public ServicioException(String mensaje) {
		super(mensaje);
	}
	
	/**
	 * Constructor
	 * @param mensaje El mensaje.
	 * @param excepcion La excepci�n producida.
	 */
	public ServicioException(String mensaje, Throwable excepcion) {
		super(mensaje, excepcion);
	}
}
