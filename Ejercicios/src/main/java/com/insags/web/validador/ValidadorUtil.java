package com.insags.web.validador;

import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.Errors;

import com.insags.poc.combos.dto.ComboDto;

/**
 * Utilería para las validaciones.
 * @author INSA
 */
public final class ValidadorUtil {

	/**
	 * Constructor.
	 */
	private ValidadorUtil() {
		
	}
	
	/**
	 * Método para validar un campo obligatorio.
	 * @param errors Los errores.
	 * @param nombre El nombre del campo.
	 * @param valor El valor del campo.
	 * @param argumentos Los argumentos adicionales.
	 */
	public static void validarCampoObligatorio(Errors errors, String nombre,
			Object valor, Object... argumentos) {
		if (errors.hasFieldErrors(nombre)) {
			return;
		}
		if (!estaInformado(valor)) {
			errors.rejectValue(nombre, "NotNull", argumentos, "Campo requerido");
		}
	}

	/**
	 * Método para comprobar si un campo está informado.
	 * @param valor El valor del campo.
	 * @return true si está informado, false en caso contrario.
	 */
	private static boolean estaInformado(Object valor) {
		if (valor == null) {
			return false;
		}
		if (valor instanceof ComboDto) {
			return estaInformado(((ComboDto) ComboDto.class.cast(valor))
					.getValor());
		}
		if (valor instanceof CharSequence) {
			return StringUtils.isNotBlank((CharSequence) CharSequence.class
					.cast(valor));
		}
		return true;
	}
}
