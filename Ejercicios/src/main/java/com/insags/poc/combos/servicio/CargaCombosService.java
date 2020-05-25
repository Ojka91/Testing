package com.insags.poc.combos.servicio;

import java.util.List;

import com.insags.poc.combos.dto.ComboDto;
import com.insags.poc.excepciones.ServicioException;

/**
 * Interfaz para la carga de combos de la aplicación.
 * @author INSA
 */
public interface CargaCombosService {

	/**
	 * Método para realizar la carga de los combos.
	 * @param nombreCombo El nombre de la tabla del combo a cargar.
	 * @return La lista de opciones disponibles para el combo.
	 * @throws ServicioException En caso de que se produzca excepción.
	 */
	List<ComboDto> buscar(String nombreCombo) throws ServicioException;
}
