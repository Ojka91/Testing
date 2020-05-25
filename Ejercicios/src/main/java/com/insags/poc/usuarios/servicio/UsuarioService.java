package com.insags.poc.usuarios.servicio;

import com.insags.poc.comun.dto.UsuarioDto;
import com.insags.poc.excepciones.ServicioException;
import com.insags.poc.usuarios.dao.UsuarioDao;
import com.insags.poc.usuarios.dto.CargaInicialBusquedaUsuariosDto;
import com.insags.poc.usuarios.dto.CargaInicialMantenimientoUsuariosDto;

/**
 * Servicio sobre la tabla USUARIOS.
 * 
 * @author INSA
 */
public interface UsuarioService {

	/**
	 * Punto de entrada de la busqueda de usuarios.
	 * 
	 * @return {@link CargaInicialBusquedaUsuariosDto}
	 * @throws ServicioException en caso de error
	 */
	CargaInicialBusquedaUsuariosDto cargaInicialBusquedas() throws ServicioException;

	/**
	 * Punto de entrada al mantenimiento de un usuario.
	 * 
	 * @return {@link CargaInicialMantenimientoUsuariosDto}
	 * @throws ServicioException en caso de error
	 */
	CargaInicialMantenimientoUsuariosDto cargaInicialMantenimiento() throws ServicioException;
	
	/**
	 * Método para la insercion de un usuario.
	 * 
	 * @param dto {@link UsuarioDto}
	 * @return {@link UsuarioDto}
	 * @throws ServicioException en caso de error
	 */
	UsuarioDto insertar(UsuarioDto dto) throws ServicioException;

	/**
	 * Se sobreescribe para eliminacion logica.
	 * 
	 * @param dto {@link UsuarioDto}
	 * @throws ServicioException en caso de error
	 */
	void eliminar(UsuarioDto dto) throws ServicioException;

	/**
	 * Metodo para establecer el dao.
	 * 
	 * @param dao {@link UsuarioDao}
	 */
	void setDao(UsuarioDao dao);
}
