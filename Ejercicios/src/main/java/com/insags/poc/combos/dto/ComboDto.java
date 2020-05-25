package com.insags.poc.combos.dto;

import java.io.Serializable;

/**
 * Clase ComboDto.
 * 
 * @author INSA
 */
public class ComboDto implements Serializable {
	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 6446290841047370063L;
	/**
	 * valor.
	 */
	private String valor;
	/**
	 * texto.
	 */
	private String texto;
	/**
	 * seleccionado.
	 */
	private boolean seleccionado;
	/**
	 * valorPadre
	 */
	private String valorPadre;

	/**
	 * Constructor por defecto.
	 */
	public ComboDto() {
	}

	/**
	 * Constructor.
	 * @param pValor El valor que le asigna a la propiedad valor y texto.
	 */
	public ComboDto(String pValor) {
		this(pValor, pValor);
	}

	/**
	 * Constructor.
	 * @param pValor El valor.
	 * @param pTexto El texto.
	 */
	public ComboDto(String pValor, String pTexto) {
		this(pValor, pTexto, null);
	}

	/**
	 * Constructor.
	 * @param pValor El valor.
	 * @param pTexto El texto.
	 * @param pValorPadre El valor del padre.
	 */
	public ComboDto(String pValor, String pTexto, String pValorPadre) {
		this();

		this.valor = pValor;
		this.texto = pTexto;
		this.valorPadre = pValorPadre;
	}

	/**
	 * Constructor.
 	 * @param that El elemento ComboDto.
	 */
	public ComboDto(ComboDto that) {
		this(that.valor, that.texto, that.valorPadre);
	}

	/**
	 * Obtiene el valor.
	 * @return el valor.
	 */
	public String getValor() {
		return this.valor;
	}

	/**
	 * Establece el valor.
	 * @param valor el valor.
	 */
	public void setValor(String valor) {
		this.valor = valor;
	}

	/**
	 * Obtiene el texto.
	 * @return el texto.
	 */
	public String getTexto() {
		return this.texto;
	}

	/**
	 * Establece el texto.
	 * @param texto el texto.
	 */
	public void setTexto(String texto) {
		this.texto = texto;
	}

	/**
	 * Obtiene si está seleccionado.
	 * @return si está seleccionado.
	 */
	public boolean isSeleccionado() {
		return this.seleccionado;
	}

	/**
	 * Establece si está seleccionado.
	 * @param pSeleccionado si está seleccionado.
	 */
	public void setSeleccionado(boolean pSeleccionado) {
		this.seleccionado = pSeleccionado;
	}

	/**
	 * Obtiene el valor del padre.
	 * @return el valor del padre.
	 */
	public String getValorPadre() {
		return this.valorPadre;
	}

	/**
	 * Establece el valor del padre.
	 * @param pValorPadre el valor del padre.
	 */
	public void setValorPadre(String pValorPadre) {
		this.valorPadre = pValorPadre;
	}
}
