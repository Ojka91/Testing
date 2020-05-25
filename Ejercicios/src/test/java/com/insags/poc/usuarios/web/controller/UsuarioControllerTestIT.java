package com.insags.poc.usuarios.web.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;

import com.insags.framework.test.base.BaseTestIntegracion;
import com.insags.framework.test.dummy.DummyValidator;

/**
 * Test de integración del controller de Usuarios.
 * @author INSA
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class UsuarioControllerTestIT extends BaseTestIntegracion {
	
	/**
	 * Controller del módulo de usuarios
	 */
	@Autowired
	private UsuarioController usuarioController;
	
	/**
	 * Mock de SpringMvc.
	 */
	private MockMvc mockMvc;
	
	/**
	 * Método para inicializar el mockeo de las clases comunes.
	 * @throws Exception En caso de error, lanzará la excepción.
	 */
	@Before
	public void initMocksWS() throws Exception {
		// Inicialización del mockeo mediante anotaciones.
		MockitoAnnotations.initMocks(this);
		
		// 1.- Creamos el mock para el controller especificado.
		StandaloneMockMvcBuilder standaloneSetup = MockMvcBuilders.standaloneSetup(usuarioController);
		standaloneSetup.setValidator(new DummyValidator());
		this.mockMvc = standaloneSetup.build();
		
	}	
	
	/**
	 * Método para testear la validación de los parámetros recibidos en el controller.
	 * Resultado esperado:
	 * 		- Los campos nombre, apellido y fechaNacimiento tienen que tener error porque son obligatorios.
	 * @throws Exception En caso de error.
	 */
	@Test
	public void existenErroresDeValidacionDeCamposObligatorios() throws Exception {
		// 1.2.- Crear el mock para poder invocar al controler.
		MockHttpServletRequestBuilder requestBuilder = 
				MockMvcRequestBuilders.post("/usuario/insertar");
		
		ResultActions resultActions = mockMvc.perform(requestBuilder);
		
		resultActions.andExpect(MockMvcResultMatchers.forwardedUrl("/jsp/insercionUsuarioKO.jsp"));
		resultActions.andExpect(MockMvcResultMatchers.model().attributeExists("usuarioDto"));
		resultActions.andExpect(MockMvcResultMatchers.model().hasErrors());
		resultActions.andExpect(MockMvcResultMatchers.model().attributeHasFieldErrors(
				"usuarioDto", "nombre", "apellido", "fechaNacimiento"));
	}
}
