package com.insags.seguridad.datos.autenticacion;

import java.util.Map;
import org.springframework.security.core.Authentication;

/**
 * Interfaz para obtener los datos de autenticacion del usuario.
 * 
 * @author INSA.
 */
public interface DatosAutenticacion extends Authentication {

	/**
	 * Metodo para obtener los datos adicionales del usuario que esta autenticado en el sistema.
	 * 
	 * @return los datos adicionales del usuario
	 */
	Map<String, ?> getDatosAdicionales();

	/**
	 * Establece los datos adionales.
	 * 
	 * @param paramMap los datos adionales
	 */
	void setDatosAdicionales(Map<String, ?> paramMap);
}
