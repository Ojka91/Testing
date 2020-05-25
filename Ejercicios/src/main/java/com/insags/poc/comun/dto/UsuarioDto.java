package com.insags.poc.comun.dto;

import java.io.Serializable;

import org.joda.time.LocalDate;

import com.filenet.api.core.Folder;

/**
 * DTO de ejemplo sobre la tabla: USUARIOS
 * @author INSA
 */
public class UsuarioDto implements Serializable {
	
	/** serialVersionUID */
	private static final long serialVersionUID = -6232711292482023151L;
	
	/** idRegistro */
	private Long idRegistro;
	
	/** nombre */
	private String nombre;
	
	/** apellido */
	private String apellido;
	
	/** fechaNacimiento */
	private LocalDate fechaNacimiento;
	
	/** idDepartamento */
	private DepartamentoDto departamento = new DepartamentoDto();
	
	/** activo */
	private boolean activo;
	
	/** codigo */
	private Integer codigo;
	
	/** idRol */
	private RolDto rol = new RolDto();
	
	/**
	 * Usuario que hace la modificación sobre el usuario.
	 */
	private UsuarioDto usuarioAuditoriaDto;
	
	/**
	 * Carpeta asociada al usuario.
	 */
	private Folder carpetaUsuario;

	/**
	 * El usuario auditor.
	 * @return the usuarioAuditoriaDto
	 */
	public UsuarioDto getUsuarioAuditoriaDto() {
		return usuarioAuditoriaDto;
	}

	/**
	 * El usuario auditor.
	 * @param usuarioAuditoriaDto the usuarioAuditoriaDto to set
	 */
	public void setUsuarioAuditoriaDto(UsuarioDto usuarioAuditoriaDto) {
		this.usuarioAuditoriaDto = usuarioAuditoriaDto;
	}

	/**
	 * Getter de nombre
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Setter de nombre
	 * @param pNombre the nombre to set
	 */
	public void setNombre(String pNombre) {
		nombre = pNombre;
	}

	/**
	 * Getter de apellido
	 * @return the apellido
	 */
	public String getApellido() {
		return apellido;
	}

	/**
	 * Setter de apellido
	 * @param pApellido the apellido to set
	 */
	public void setApellido(String pApellido) {
		apellido = pApellido;
	}

	/**
	 * Getter de fechaNacimiento
	 * @return the fechaNacimiento
	 */
	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	/**
	 * Setter de fechaNacimiento
	 * @param pFechaNacimiento the fechaNacimiento to set
	 */
	public void setFechaNacimiento(LocalDate pFechaNacimiento) {
		fechaNacimiento = pFechaNacimiento;
	}

	/**
	 * Getter de departamento
	 * @return the departamento
	 */
	public DepartamentoDto getDepartamento() {
		return departamento;
	}

	/**
	 * Setter de departamento
	 * @param pDepartamento the departamento to set
	 */
	public void setDepartamento(DepartamentoDto pDepartamento) {
		departamento = pDepartamento;
	}

	/**
	 * Getter de activo
	 * @return the activo
	 */
	public boolean isActivo() {
		return activo;
	}

	/**
	 * Setter de activo
	 * @param pActivo the activo to set
	 */
	public void setActivo(boolean pActivo) {
		activo = pActivo;
	}

	/**
	 * Getter de codigo
	 * @return the codigo
	 */
	public Integer getCodigo() {
		return codigo;
	}

	/**
	 * Setter de codigo
	 * @param pCodigo the codigo to set
	 */
	public void setCodigo(Integer pCodigo) {
		codigo = pCodigo;
	}

	/**
	 * Getter de rol
	 * @return the rol
	 */
	public RolDto getRol() {
		return rol;
	}

	/**
	 * Setter de rol
	 * @param pRol the rol to set
	 */
	public void setRol(RolDto pRol) {
		rol = pRol;
	}

	/**
	 * Obtiene la carpeta del usuario.
	 * @return the carpetaUsuario.
	 */
	public Folder getCarpetaUsuario() {
		return carpetaUsuario;
	}

	/**
	 * Establece la carpeta del usuario.
	 * @param carpetaUsuario the carpetaUsuario to set.
	 */
	public void setCarpetaUsuario(Folder carpetaUsuario) {
		this.carpetaUsuario = carpetaUsuario;
	}

	/**
	 * Obtiene el idRegistro
	 * @return the idRegistro
	 */
	public Long getIdRegistro() {
		return idRegistro;
	}

	/**
	 * Establece el idRegistro.
	 * @param idRegistro the idRegistro to set
	 */
	public void setIdRegistro(Long idRegistro) {
		this.idRegistro = idRegistro;
	}
}
