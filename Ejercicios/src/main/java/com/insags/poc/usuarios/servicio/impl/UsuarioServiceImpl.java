package com.insags.poc.usuarios.servicio.impl;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.filenet.api.core.Folder;
import com.insags.poc.combos.servicio.CargaCombosService;
import com.insags.poc.comun.constantes.ComboConstantes;
import com.insags.poc.comun.dto.UsuarioDto;
import com.insags.poc.excepciones.FilenetException;
import com.insags.poc.excepciones.ServicioException;
import com.insags.poc.filenet.dao.FilenetDao;
import com.insags.poc.usuarios.dao.UsuarioDao;
import com.insags.poc.usuarios.dto.CargaInicialBusquedaUsuariosDto;
import com.insags.poc.usuarios.dto.CargaInicialMantenimientoUsuariosDto;
import com.insags.poc.usuarios.servicio.UsuarioService;
import com.insags.seguridad.datos.autenticacion.DatosAutenticacion;

/**
 * Implementacion del servicio de ejemplo sobre la tabla USUARIOS.
 * 
 * @author INSA
 */
@Service("UsuarioService")
public class UsuarioServiceImpl implements UsuarioService {

	private static final Logger LOG = Logger.getLogger(UsuarioServiceImpl.class.getName());

	@Autowired
	private UsuarioDao dao;

	@Autowired
	private CargaCombosService servicioCargaCombos;

	@Autowired
	private FilenetDao filenetDao;

	@Override
	public CargaInicialBusquedaUsuariosDto cargaInicialBusquedas() throws ServicioException {
		CargaInicialBusquedaUsuariosDto dto = new CargaInicialBusquedaUsuariosDto();
		dto.setDepartamentos(this.servicioCargaCombos.buscar(ComboConstantes.DEPARTAMENTOS));
		dto.setActivos(this.servicioCargaCombos.buscar(ComboConstantes.SI_NO_TODOS));
		dto.setRoles(this.servicioCargaCombos.buscar(ComboConstantes.ROLES));
		return dto;
	}

	@Override
	public CargaInicialMantenimientoUsuariosDto cargaInicialMantenimiento() throws ServicioException {
		CargaInicialMantenimientoUsuariosDto dto = new CargaInicialMantenimientoUsuariosDto();
		dto.setDepartamentos(this.servicioCargaCombos.buscar(ComboConstantes.DEPARTAMENTOS));
		dto.setRoles(this.servicioCargaCombos.buscar(ComboConstantes.ROLES));
		return dto;
	}

	@Override
	public UsuarioDto insertar(UsuarioDto dto) throws ServicioException {
		try {
			this.filenetDao.conectar();
		} catch (Exception e) {
			LOG.error("Se ha producido un error con la conexion filenet", e);
			throw new FilenetException(e.getMessage());
		}

		try {
			Folder carpetaUsuario = this.filenetDao.crearCarpetaUsuario(dto);
			dto.setCarpetaUsuario(carpetaUsuario);
		} catch (Exception e) {
			LOG.error("Se ha producido un error al crear la carpeta del usuario", e);
			throw new ServicioException(e.getMessage());
		} finally {
			try {
				this.filenetDao.desconectar();
			} catch (Exception e) {
				LOG.error("Se ha producido un error al desconectar de filenet", e);
				throw new ServicioException(e.getMessage());
			}
		}

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		Map<String, ?> datosUsuario = DatosAutenticacion.class.cast(authentication).getDatosAdicionales();

		UsuarioDto usuarioAuditoriaDto = new UsuarioDto();
		usuarioAuditoriaDto.setIdRegistro(Long.valueOf(datosUsuario.get("ID_USUARIO").toString()));
		usuarioAuditoriaDto.setCodigo(Integer.valueOf(datosUsuario.get("USUARIO_CODIGO").toString()));

		dto.setUsuarioAuditoriaDto(usuarioAuditoriaDto);

		return dao.insertar(dto);
	}

	@Override
	public void eliminar(UsuarioDto dto) throws ServicioException {
		boolean activo = dto.isActivo();

		try {
			dto.setActivo(false);
			dao.eliminar(dto);
			activo = dto.isActivo();
		} finally {
			dto.setActivo(activo);
		}
	}

	@Override
	public void setDao(UsuarioDao dao) {
		this.dao = dao;
	}
}
