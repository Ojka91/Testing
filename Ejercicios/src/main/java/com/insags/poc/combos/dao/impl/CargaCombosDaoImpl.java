package com.insags.poc.combos.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.insags.poc.combos.dao.CargaCombosDao;
import com.insags.poc.combos.dto.ComboDto;

/**
 * Clase para la carga de combos.
 * @author INSA
 */
@Repository("COMBOS")
public class CargaCombosDaoImpl implements CargaCombosDao {

	/**
	 * Método para realizar la carga de los combos.
	 * @param nombreCombo El nombre de la tabla del combo a cargar.
	 * @return La lista de opciones disponibles para el combo.
	 */
	public List<ComboDto> buscar(String nombreCombo) {
		// Aquí conectará con la BBDD y realizará la búsqueda.
		throw new RuntimeException(
				"CargaCombosDaoImpl.buscar: Tienes que hacer un mock de este método para simular el comportamiento");
	}
}
