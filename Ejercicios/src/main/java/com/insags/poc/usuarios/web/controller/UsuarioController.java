package com.insags.poc.usuarios.web.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.insags.poc.comun.dto.UsuarioDto;
import com.insags.poc.excepciones.ServicioException;
import com.insags.poc.usuarios.dto.CargaInicialMantenimientoUsuariosDto;
import com.insags.poc.usuarios.servicio.UsuarioService;
import com.insags.web.validador.ValidadorUtil;

/**
 * Controller de ejemplo sobre la tabla USUARIOS
 * 
 * @author INSA
 */
@Controller
@RequestMapping("/usuario")
public class UsuarioController {

	/** servicio */
	@Autowired
	private UsuarioService servicio;

	/**
	 * Punto de entrada al popup de administración de un usuario
	 * 
	 * @param model Model
	 * @return String con la vista
	 * @throws ServicioException en caso de error
	 */
	@RequestMapping(value = "/cargaInicialMantenimiento", method = RequestMethod.POST)
	public String editar(Model model) throws ServicioException {

		// Inicializa un CargaInicialMantenimientoUsuariosDto y lo añade al
		// modelo
		CargaInicialMantenimientoUsuariosDto dtoCargaInicial = this.servicio.cargaInicialMantenimiento();
		model.addAttribute(dtoCargaInicial);

		// Retorna a la vista "popupUsuario"
		return "/jsp/insercionUsuario.jsp";
	}

	/**
	 * Inserta un elemento
	 * 
	 * @param model Model
	 * @param pDto E con el elemento a insertar o modificar
	 * @param bindingResult BindingResult
	 * @param errors Errors
	 * @param locale Locale
	 * @param request HttpServletRequest
	 * @return La vista a la que tiene que redirigir.
	 * @throws Exception en caso de error
	 */
	@RequestMapping(value = "/insertar", method = RequestMethod.POST)
	public String insertar(Model model, UsuarioDto pDto, BindingResult bindingResult, Errors errors, Locale locale,
			HttpServletRequest request) throws Exception {

		ValidadorUtil.validarCampoObligatorio(errors, "nombre", pDto.getNombre(), "i18n.usuarioDto.nombre");
		ValidadorUtil.validarCampoObligatorio(errors, "apellido", pDto.getApellido(), "i18n.usuarioDto.apellido");
		ValidadorUtil.validarCampoObligatorio(errors, "fechaNacimiento", pDto.getFechaNacimiento(), "i18n.usuarioDto.fechaNacimiento");

		if (errors.hasErrors()) {
			return "/jsp/insercionUsuarioKO.jsp";
		}

		// Inserción propiamente dicha
		model.addAttribute("usuarioDto", this.servicio.insertar(pDto));

		return "/jsp/insercionUsuarioOK.jsp";
	}
}
