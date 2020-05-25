package com.insags.poc.usuarios.dto;

import java.io.Serializable;
import java.util.List;

import com.insags.poc.combos.dto.ComboDto;

/**
 * DTO de carga inicial de un mantenimiento de ejemplo
 * @author INSA
 */
public class CargaInicialMantenimientoUsuariosDto implements Serializable {
	
	/** serialVersionUID */
	private static final long serialVersionUID = 8724355282031375495L;
	
	/** departamentos */
	private List<ComboDto> departamentos;
	/** roles */
	private List<ComboDto> roles;
	
	/**
	 * Getter de departamentos
	 * @return the departamentos
	 */
	public List<ComboDto> getDepartamentos() {
		return departamentos;
	}
	/**
	 * Setter de departamentos
	 * @param pDepartamentos the departamentos to set
	 */
	public void setDepartamentos(List<ComboDto> pDepartamentos) {
		departamentos = pDepartamentos;
	}
	/**
	 * Getter de roles
	 * @return the roles
	 */
	public List<ComboDto> getRoles() {
		return roles;
	}
	/**
	 * Setter de roles
	 * @param pRoles the roles to set
	 */
	public void setRoles(List<ComboDto> pRoles) {
		roles = pRoles;
	}

}
