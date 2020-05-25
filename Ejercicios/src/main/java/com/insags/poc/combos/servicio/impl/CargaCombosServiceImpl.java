package com.insags.poc.combos.servicio.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insags.poc.combos.dao.CargaCombosDao;
import com.insags.poc.combos.dto.ComboDto;
import com.insags.poc.combos.servicio.CargaCombosService;
import com.insags.poc.excepciones.ServicioException;

/**
 * Clase para la carga de combos de la aplicación.
 * @author INSA
 */
@Service("CargaCombosServicio")
public class CargaCombosServiceImpl implements CargaCombosService {

	/**
	 * Dao.
	 */
	@Autowired
	private CargaCombosDao dao;

	/**
	 * Método para realizar la carga de los combos.
	 * @param nombreCombo El nombre de la tabla del combo a cargar.
	 * @return La lista de opciones disponibles para el combo.
	 * @throws ServicioException En caso de que se produzca excepción.
	 */
	public List<ComboDto> buscar(String nombreCombo) throws ServicioException {
		return this.dao.buscar(nombreCombo);
	}

}
