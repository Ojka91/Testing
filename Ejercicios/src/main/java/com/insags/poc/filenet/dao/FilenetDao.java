package com.insags.poc.filenet.dao;

import com.filenet.api.core.Connection;
import com.filenet.api.core.Folder;
import com.insags.poc.comun.dto.UsuarioDto;

/**
 * Dao para el acceso a filenet.
 * @author INSA.
 */
public interface FilenetDao {

	/**
	 * Método para conectar a filenet.
	 * @return La conexión.
	 * @throws Exception En caso de error de conexión.
	 */
	Connection conectar() throws Exception;
	
	
	/**
	 * Método para desconectar de filenet.
	 * @throws Exception En caso de error de conexión.
	 */
	void desconectar() throws Exception;
	
	/**
	 * Método para crear la carpeta del usuario en filenet.
	 * @param dto El usuario
	 * @return La carpeta creada.
	 */
	Folder crearCarpetaUsuario(UsuarioDto dto);
	
}
