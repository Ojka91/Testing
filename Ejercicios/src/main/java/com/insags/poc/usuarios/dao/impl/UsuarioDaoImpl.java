package com.insags.poc.usuarios.dao.impl;

import org.springframework.stereotype.Repository;

import com.insags.poc.comun.dto.UsuarioDto;
import com.insags.poc.usuarios.dao.UsuarioDao;

/**
 * Implementaci�n del DAO de ejemplo sobre la tabla USUARIOS
 * @author INSA
 */
@Repository("UsuarioDao")
public class UsuarioDaoImpl implements UsuarioDao {

	
	@Override
	public UsuarioDto insertar(UsuarioDto dto) {
		// Aqu� conectar� con la BBDD y realizar� la inserci�n.
		throw new RuntimeException(
				"UsuarioDaoImpl.insertar: " 
				+ "Tienes que hacer un mock de este m�todo para simular el comportamiento");
	}

	/* (non-Javadoc)
	 * @see com.insags.poc.usuarios.dao.UsuarioDao#eliminar(com.insags.poc.comun.dto.UsuarioDto)
	 */
	@Override
	public void eliminar(UsuarioDto dto) {
		// Aqu� conectar� con la BBDD y realizar� la eliminaci�n.
		throw new RuntimeException(
				"UsuarioDaoImpl.eliminar: " 
				+ "Tienes que hacer un mock de este m�todo para simular el comportamiento");
	}
	
	
}
