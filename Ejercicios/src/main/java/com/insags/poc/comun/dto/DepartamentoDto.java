package com.insags.poc.comun.dto;

import java.io.Serializable;

/**
 * DTO de ejemplo sobre la tabla: DEPARTAMENTOS
 * 
 * @author INSA
 */
public class DepartamentoDto implements Serializable {

	/** serialVersionUID */
	private static final long serialVersionUID = -8576471793246869078L;

	/** idRegistro */
	private Long idRegistro;

	/** nombre */
	private String nombre;

	/**
	 * Getter de nombre
	 * 
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Setter de nombre
	 * 
	 * @param pNombre
	 *            the nombre to set
	 */
	public void setNombre(String pNombre) {
		nombre = pNombre;
	}

	/**
	 * Obtiene el idRegistro.
	 * @return the idRegistro
	 */
	public Long getIdRegistro() {
		return idRegistro;
	}

	/**
	 * Establece el idRegistro.
	 * @param idRegistro
	 *            the idRegistro to set
	 */
	public void setIdRegistro(Long idRegistro) {
		this.idRegistro = idRegistro;
	}
}
