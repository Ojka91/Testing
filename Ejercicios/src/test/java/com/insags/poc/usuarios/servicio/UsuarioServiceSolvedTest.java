package com.insags.poc.usuarios.servicio;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.anyObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.filenet.api.core.Folder;
import com.insags.poc.combos.dto.ComboDto;
import com.insags.poc.combos.servicio.CargaCombosService;
import com.insags.poc.comun.constantes.ComboConstantes;
import com.insags.poc.comun.dto.UsuarioDto;
import com.insags.poc.excepciones.FilenetException;
import com.insags.poc.excepciones.ServicioException;
import com.insags.poc.filenet.dao.FilenetDao;
import com.insags.poc.usuarios.dao.UsuarioDao;
import com.insags.poc.usuarios.dto.CargaInicialMantenimientoUsuariosDto;
import com.insags.poc.usuarios.servicio.impl.UsuarioServiceSolvedImpl;

@RunWith(PowerMockRunner.class)
@PrepareForTest(UsuarioServiceSolvedImpl.class)
public class UsuarioServiceSolvedTest {

	@Mock
	private CargaCombosService cargaCombosService;

	@Mock
	private FilenetDao filenetDao;

	@Mock
	private UsuarioDao usuarioDao;

	@Spy
	@InjectMocks
	private UsuarioService usuarioService = new UsuarioServiceSolvedImpl();

	@SuppressWarnings("unchecked")
	@Test(expected = ServicioException.class)
	public void testCargaInicialMantenimientoControlExcepcionesExahustivo() throws ServicioException {
		// ARRANGE

		try {
			Mockito.when(cargaCombosService.buscar(ComboConstantes.DEPARTAMENTOS)).thenThrow(ServicioException.class);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		// ACT

		try {
			usuarioService.cargaInicialMantenimiento();
		} catch (ServicioException e) {
			// ASSERT

			Mockito.verify(cargaCombosService, Mockito.times(1)).buscar(ComboConstantes.DEPARTAMENTOS);
			Mockito.verify(cargaCombosService, Mockito.never()).buscar(ComboConstantes.ROLES);

			assertNotNull(e);
			assertTrue(e instanceof ServicioException);

			throw e;
		}

		fail("A estas alturas del test se deberia haber lanzado una excepcion de tipo ServicioException");
	}

	@Test
	public void testCargaInicialMantenimientoControlNumeroInvocaciones() throws ServicioException {
		// ARRANGE

		ComboDto departamento1 = new ComboDto("1", "Departamento1");
		ComboDto departamento2 = new ComboDto("2", "Departamento2");

		List<ComboDto> departamentos = Arrays.asList(departamento1, departamento2);

		Mockito.when(cargaCombosService.buscar(ComboConstantes.DEPARTAMENTOS)).thenReturn(departamentos);

		ComboDto rol1 = new ComboDto("1", "Rol1");
		ComboDto rol2 = new ComboDto("2", "Rol2");
		ComboDto rol3 = new ComboDto("3", "Rol3");

		List<ComboDto> roles = Arrays.asList(rol1, rol2, rol3);

		Mockito.when(cargaCombosService.buscar(ComboConstantes.ROLES)).thenReturn(roles);

		CargaInicialMantenimientoUsuariosDto cargaInicial = null;

		// ACT

		try {
			cargaInicial = usuarioService.cargaInicialMantenimiento();
		} catch (ServicioException e) {
			fail("El metodo bajo pruebas no deberia haber lanzado una excepcion.");
		}

		// ASSERT

		Mockito.verify(cargaCombosService, Mockito.times(1)).buscar(ComboConstantes.DEPARTAMENTOS);
		Mockito.verify(cargaCombosService, Mockito.times(1)).buscar(ComboConstantes.ROLES);

		assertNotNull(cargaInicial);
		assertNotNull(cargaInicial.getDepartamentos());
		assertTrue(cargaInicial.getDepartamentos().contains(departamento1));
		assertTrue(cargaInicial.getDepartamentos().contains(departamento2));
		assertEquals(cargaInicial.getDepartamentos(), departamentos);

		assertNotNull(cargaInicial);
		assertNotNull(cargaInicial.getRoles());
		assertTrue(cargaInicial.getRoles().contains(rol1));
		assertTrue(cargaInicial.getRoles().contains(rol2));
		assertTrue(cargaInicial.getRoles().contains(rol3));
		assertEquals(cargaInicial.getRoles(), roles);

		assertThat(cargaInicial.getDepartamentos(), is(not(empty())));
		assertThat(cargaInicial.getDepartamentos(), is(departamentos));
		assertThat(cargaInicial.getDepartamentos(), contains(departamentos.toArray(new ComboDto[0])));
		assertThat(cargaInicial.getRoles(), is(roles));
		assertThat(cargaInicial.getRoles(), hasItems(roles.toArray(new ComboDto[0])));
	}

	@Test(expected = FilenetException.class)
	public void testInsertarErrorConexionFilenet() throws Exception {
		// ARRANGE

		Mockito.when(filenetDao.conectar()).thenThrow(new Exception());

		UsuarioDto dto = new UsuarioDto();

		// ACT

		try {
			usuarioService.insertar(dto);
		} catch (ServicioException e) {
			// ASSERT

			Mockito.verify(filenetDao, Mockito.times(1)).conectar();
			Mockito.verify(filenetDao, Mockito.never()).crearCarpetaUsuario(UsuarioDto.class.cast(org.mockito.Matchers.anyObject()));

			final String message = "Se ha producido un error con la conexion filenet";

			assertNotNull(e);
			assertTrue(e instanceof FilenetException);
			assertEquals(e.getMessage(), message);

			assertThat(e, is(instanceOf(FilenetException.class)));
			assertThat(e.getMessage(), is(message));

			throw e;
		}

		fail("Se deberia haber lanzado una FileException");
	}

	@SuppressWarnings("unchecked")
	@Test(expected = FilenetException.class)
	public void testInsertarErrorCreacionCarpeta() throws Exception {
		// ARRANGE

		Mockito.when(filenetDao.conectar()).thenReturn(null);
		Mockito.when(filenetDao.crearCarpetaUsuario(UsuarioDto.class.cast(anyObject()))).thenThrow(RuntimeException.class);

		// ACT

		try {
			usuarioService.insertar(new UsuarioDto());
		} catch (ServicioException e) {
			// ASSERT

			Mockito.verify(filenetDao, Mockito.times(1)).conectar();
			Mockito.verify(filenetDao, Mockito.times(1)).crearCarpetaUsuario(UsuarioDto.class.cast(anyObject()));
			Mockito.verify(filenetDao, Mockito.times(1)).desconectar();

			String message = "Se ha producido un error al crear la carpeta del usuario";

			assertNotNull(e);
			assertTrue(e instanceof FilenetException);
			assertEquals(e.getMessage(), message);

			assertThat(e, is(instanceOf(FilenetException.class)));
			assertThat(e.getMessage(), is(message));

			throw e;
		}

		fail("Se deberia haber lanzado una FileException");
	}
	
	@Test(expected = FilenetException.class)
	public void testInsertarErrorDesconectarFilenet() throws Exception {
		// ARRANGE

		Mockito.when(filenetDao.conectar()).thenReturn(null);
		Mockito.when(filenetDao.crearCarpetaUsuario(UsuarioDto.class.cast(anyObject()))).thenReturn(Mockito.mock(Folder.class));
		Mockito.doThrow(RuntimeException.class).when(filenetDao).desconectar();

		// ACT

		try {
			usuarioService.insertar(new UsuarioDto());
		} catch (ServicioException e) {
			// ASSERT

			Mockito.verify(filenetDao, Mockito.times(1)).conectar();
			Mockito.verify(filenetDao, Mockito.times(1)).crearCarpetaUsuario(UsuarioDto.class.cast(anyObject()));
			Mockito.verify(filenetDao, Mockito.times(1)).desconectar();

			String message = "Se ha producido un error al desconectar de filenet";

			assertNotNull(e);
			assertTrue(e instanceof FilenetException);
			assertEquals(e.getMessage(), message);

			assertThat(e, is(instanceOf(FilenetException.class)));
			assertThat(e.getMessage(), is(message));

			throw e;
		}

		fail("Se deberia haber lanzado una FileException");
	}
	
	@Test
	public void testInsertarObtencionDatosUsuariosParametroNull() throws Exception {
		// ARRANGE
		
		UsuarioDto usuario = null;
		
		// ACT

		try {
			usuario = usuarioService.insertar(usuario);
		} catch (ServicioException ex) {
			fail("No se esperaba el lanzamiento de una excepcion.");
		}
		
		// ASSERT

		assertNull(usuario);
	}

	@Test
	public void testInsertarObtencionDatosUsuarios() throws Exception {
		// ARRANGE
		
		Mockito.when(filenetDao.conectar()).thenReturn(null);
		Mockito.when(filenetDao.crearCarpetaUsuario(UsuarioDto.class.cast(anyObject()))).thenReturn(Mockito.mock(Folder.class));
		Mockito.doNothing().when(filenetDao).desconectar();
		
		Map<String, Object> usuarios = new HashMap<String, Object>();
		usuarios.put("ID_USUARIO", Long.valueOf(33));
		usuarios.put("USUARIO_CODIGO", Integer.valueOf(4285));
		
		PowerMockito.doReturn(usuarios).when(usuarioService, "obtenerDatosUsuario");

		Mockito.when(usuarioDao.insertar(UsuarioDto.class.cast(org.mockito.Matchers.anyObject()))).thenAnswer(new Answer<UsuarioDto>() {
			@Override
			public UsuarioDto answer(InvocationOnMock invocation) throws Throwable {
				UsuarioDto usuario = (UsuarioDto) invocation.getArguments()[0];
				usuario.setIdRegistro(77l);
				return usuario;
			}
		});

		// ACT

		UsuarioDto usuario = null;

		try {
			usuario = usuarioService.insertar(new UsuarioDto());
		} catch (ServicioException ex) {
			fail("No se esperaba el lanzamiento de una excepcion.");
		}
		
		// ASSERT

		Mockito.verify(filenetDao, Mockito.times(1)).conectar();
		Mockito.verify(filenetDao, Mockito.times(1)).crearCarpetaUsuario(UsuarioDto.class.cast(anyObject()));
		Mockito.verify(filenetDao, Mockito.times(1)).desconectar();

		PowerMockito.verifyPrivate(usuarioService, Mockito.times(1)).invoke("obtenerDatosUsuario");
		Mockito.verify(usuarioDao).insertar(UsuarioDto.class.cast(anyObject()));
		
		assertNotNull(usuario);
		assertThat(usuario.getIdRegistro(), is(77l));
		assertNotNull(usuario.getUsuarioAuditoriaDto());
		assertThat(usuario.getUsuarioAuditoriaDto().getCodigo(), is(4285));
		assertThat(usuario.getUsuarioAuditoriaDto().getIdRegistro(), is(33l));
		assertNotNull(usuario.getCarpetaUsuario());
	}
}
