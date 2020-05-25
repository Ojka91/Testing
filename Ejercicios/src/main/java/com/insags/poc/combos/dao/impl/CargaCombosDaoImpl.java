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
	 * M�todo para realizar la carga de los combos.
	 * @param nombreCombo El nombre de la tabla del combo a cargar.
	 * @return La lista de opciones disponibles para el combo.
	 */
	public List<ComboDto> buscar(String nombreCombo) {
		// Aqu� conectar� con la BBDD y realizar� la b�squeda.
		throw new RuntimeException(
				"CargaCombosDaoImpl.buscar: Tienes que hacer un mock de este m�todo para simular el comportamiento");
	}
}
