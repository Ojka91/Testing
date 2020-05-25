package com.insags.poc.usuarios.dao;

import com.insags.poc.comun.dto.UsuarioDto;

/**
 * DAO de ejemplo sobre la tabla USUARIOS
 * @author INSA
 */
public interface UsuarioDao  {

	/**
	 * M�todo que inserta un usuario en la BBDD.
	 * @param dto El dto con los datos del usuario.
	 * @return El dto con el idRegistro relleno.
	 */
	UsuarioDto insertar(UsuarioDto dto);
	
	/**
	 * M�todo que elimina un usuario en la BBDD.
	 * @param dto El dto con los datos del usuario.
	 */
	void eliminar(UsuarioDto dto);
}
