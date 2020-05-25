package com.insags.poc.excepciones;


/**
 * Clase FilenetException.<br>
 * @author INSA
 */
public class FilenetException extends ServicioException {
    
    /**
     *	Atributo serialVersionUID.<br>
     */
    private static final long serialVersionUID = 7851591209922075959L;

    /**
     * Constructor.
     * @param mensaje El mensaje.
     */
    public FilenetException(String mensaje) {
    
        super(mensaje);
    }
    
    /**
     * Constructor.
     * @param mensaje El mensaje.
     * @param exception La excepción.
     */
    public FilenetException(String mensaje, Throwable exception) {
    
        super(mensaje, exception);
    }
}
