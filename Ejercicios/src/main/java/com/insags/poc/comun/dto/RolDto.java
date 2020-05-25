package com.insags.poc.comun.dto;

import java.io.Serializable;

/**
 * DTO de ejemplo sobre la tabla: ROLES
 * @author INSA
 */
public class RolDto implements Serializable {
	
	/** serialVersionUID */
	private static final long serialVersionUID = -7997675255198301621L;
	
	/** rol */
	private String rol;

	/**
	 * Getter de rol
	 * @return the rol
	 */
	public String getRol() {
		return rol;
	}

	/**
	 * Setter de rol
	 * @param pRol the rol to set
	 */
	public void setRol(String pRol) {
		rol = pRol;
	}

}
