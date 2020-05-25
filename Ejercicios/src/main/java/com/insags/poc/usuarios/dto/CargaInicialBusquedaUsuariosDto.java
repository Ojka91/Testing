package com.insags.poc.usuarios.dto;

import java.io.Serializable;
import java.util.List;

import com.insags.poc.combos.dto.ComboDto;

/**
 * DTO de carga inicial de una búsqueda de ejemplo
 * @author INSA
 */
public class CargaInicialBusquedaUsuariosDto implements Serializable {
	
	/** serialVersionUID */
	private static final long serialVersionUID = 8231452673252522502L;
	
	/** departamentos */
	private List<ComboDto> departamentos;
	/** activos */
	private List<ComboDto> activos;
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
	 * Getter de activos
	 * @return the activos
	 */
	public List<ComboDto> getActivos() {
		return activos;
	}
	/**
	 * Setter de activos
	 * @param pActivos the activos to set
	 */
	public void setActivos(List<ComboDto> pActivos) {
		activos = pActivos;
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