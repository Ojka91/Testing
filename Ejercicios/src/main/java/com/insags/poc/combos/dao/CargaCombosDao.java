package com.insags.poc.combos.dao;

import java.util.List;

import com.insags.poc.combos.dto.ComboDto;

/**
 * Interfaz para la carga de combos.
 * @author INSA
 *
 */
public interface CargaCombosDao {
	
	/**
	 * Método para realizar la carga de los combos.
	 * @param nombreCombo El nombre de la tabla del combo a cargar.
	 * @return La lista de opciones disponibles para el combo.
	 */
	List<ComboDto> buscar(String nombreCombo);
}
