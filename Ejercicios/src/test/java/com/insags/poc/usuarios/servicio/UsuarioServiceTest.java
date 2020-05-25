package com.insags.poc.usuarios.servicio;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.insags.poc.combos.dto.ComboDto;
import com.insags.poc.combos.servicio.CargaCombosService;
import com.insags.poc.comun.constantes.ComboConstantes;
import com.insags.poc.comun.dto.UsuarioDto;
import com.insags.poc.excepciones.FilenetException;
import com.insags.poc.excepciones.ServicioException;
import com.insags.poc.filenet.dao.FilenetDao;
import com.insags.poc.usuarios.dao.UsuarioDao;
import com.insags.poc.usuarios.dto.CargaInicialMantenimientoUsuariosDto;
import com.insags.poc.usuarios.servicio.impl.UsuarioServiceImpl;

@RunWith(PowerMockRunner.class)
@PrepareForTest(UsuarioServiceImpl.class)
public class UsuarioServiceTest {
	@Mock
	private UsuarioDao dao;

	@Mock
	private CargaCombosService servicioCargaCombos;

	@Mock
	private FilenetDao filenetDao;
	
	@Spy
	@InjectMocks
	private UsuarioService servicio = new UsuarioServiceImpl();

	@Test(expected = ServicioException.class)
	public void testCargaInicialMantenimientoControlExcepciones() throws ServicioException{
//		Mockito.when(servicioCargaCombos.buscar(anyString())).thenThrow(ServicioException.class);
		doThrow(ServicioException.class).when(servicioCargaCombos).buscar(anyString());
		
		try {
			servicio.cargaInicialMantenimiento();
		}catch (ServicioException e) {
			verify(servicioCargaCombos, times(1)).buscar(ComboConstantes.DEPARTAMENTOS);
//			verify(servicioCargaCombos, never()).buscar(ComboConstantes.ROLES);
			verify(servicioCargaCombos, times(0)).buscar(ComboConstantes.ROLES);
			
			throw e;
		}catch (Exception ex) {
			fail("ERROR exception no es de tipo ServicioException");
		}
	}
	
	
	@Test
	public void testCargaInicialMantenimientoControlNumeroinvocaciones() throws ServicioException{
		
		ComboDto combo1 = new ComboDto("1", "departamento1");
		ComboDto combo2 = new ComboDto("2", "departamento2");
		
		List<ComboDto> departamentos = Arrays.asList(combo1, combo2);
		
		ComboDto rol1 = new ComboDto("1", "rol1");
		ComboDto rol2 = new ComboDto("2", "rol2");
		ComboDto rol3 = new ComboDto("3", "rol3");
		
		List<ComboDto> roles = Arrays.asList(rol1, rol2, rol3);
		
		
		when(servicioCargaCombos.buscar(ComboConstantes.DEPARTAMENTOS)).thenReturn(departamentos);
		when(servicioCargaCombos.buscar(ComboConstantes.ROLES)).thenReturn(roles);
		
		
		CargaInicialMantenimientoUsuariosDto resultado = servicio.cargaInicialMantenimiento();
		
		verify(servicioCargaCombos).buscar(ComboConstantes.DEPARTAMENTOS);
		verify(servicioCargaCombos).buscar(ComboConstantes.ROLES);
		
//		verify(servicioCargaCombos, times(2)).buscar(anyString());
		
		assertNotNull(resultado);
		assertNotNull(resultado.getDepartamentos());
		assertNotNull(resultado.getRoles());
		
		assertThat(resultado.getDepartamentos(), is(departamentos));
		assertTrue(resultado.getDepartamentos().contains(combo1));
		assertTrue(resultado.getDepartamentos().contains(combo2));
		
		assertThat(resultado.getRoles(), is(roles));
		assertTrue(resultado.getRoles().contains(rol1));
		assertTrue(resultado.getRoles().contains(rol2));
		assertTrue(resultado.getRoles().contains(rol3));
	}
	
	
	
	@Test(expected = FilenetException.class)
	public void testInsertarErrorConexion() throws Exception{
//		when(filenetDao.conectar()).thenThrow(FilenetException.class);
		when(filenetDao.conectar()).thenThrow(new FilenetException("Se ha producido un error al conectar con Filenet"));
		
		try {
			servicio.insertar(new UsuarioDto());
		} catch (FilenetException fe) {
			verify(filenetDao).conectar();
			verify(filenetDao, times(0)).crearCarpetaUsuario(any());
			verify(filenetDao, never()).desconectar();
			
			assertThat(fe.getMessage(), equalTo("Se ha producido un error al conectar con Filenet"));
			
			throw fe;
		}
		
		
	}
}
