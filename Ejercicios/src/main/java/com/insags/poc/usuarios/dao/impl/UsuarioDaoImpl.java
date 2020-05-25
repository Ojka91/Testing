package com.insags.poc.usuarios.dao.impl;

import org.springframework.stereotype.Repository;

import com.insags.poc.comun.dto.UsuarioDto;
import com.insags.poc.usuarios.dao.UsuarioDao;

/**
 * Implementación del DAO de ejemplo sobre la tabla USUARIOS
 * @author INSA
 */
@Repository("UsuarioDao")
public class UsuarioDaoImpl implements UsuarioDao {

	
	@Override
	public UsuarioDto insertar(UsuarioDto dto) {
		// Aquí conectará con la BBDD y realizará la inserción.
		throw new RuntimeException(
				"UsuarioDaoImpl.insertar: " 
				+ "Tienes que hacer un mock de este método para simular el comportamiento");
	}

	/* (non-Javadoc)
	 * @see com.insags.poc.usuarios.dao.UsuarioDao#eliminar(com.insags.poc.comun.dto.UsuarioDto)
	 */
	@Override
	public void eliminar(UsuarioDto dto) {
		// Aquí conectará con la BBDD y realizará la eliminación.
		throw new RuntimeException(
				"UsuarioDaoImpl.eliminar: " 
				+ "Tienes que hacer un mock de este método para simular el comportamiento");
	}
	
	
}
