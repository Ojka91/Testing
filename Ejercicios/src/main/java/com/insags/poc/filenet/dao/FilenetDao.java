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
	 * M�todo para conectar a filenet.
	 * @return La conexi�n.
	 * @throws Exception En caso de error de conexi�n.
	 */
	Connection conectar() throws Exception;
	
	
	/**
	 * M�todo para desconectar de filenet.
	 * @throws Exception En caso de error de conexi�n.
	 */
	void desconectar() throws Exception;
	
	/**
	 * M�todo para crear la carpeta del usuario en filenet.
	 * @param dto El usuario
	 * @return La carpeta creada.
	 */
	Folder crearCarpetaUsuario(UsuarioDto dto);
	
}
