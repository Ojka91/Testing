package com.viewnext;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ItemController {
	public static final String PATRON_FECHA = "dd/MM/yyyy HH:mm:ss";
	private Boolean estadoValidacion = Boolean.FALSE;

	public static String[] obtenerItems() {
		return ItemHelper.obtenerItems();
	}

	public static void imprimirItems() {
		ItemHelper.imprimirItems();
	}

	public static void saludar() {
		SaludosHelper.saludar();
	}

	public void realizarValidacion() {
		if (obtenerEstadoValidacion()) {
			System.out.println("Validando datos");
		} else {
			throw new RuntimeException();
		}
	}

	public String obtenerFechaComoCadena() {
		Date fechaActual = new Date();
		SimpleDateFormat formateadorFecha = new SimpleDateFormat (PATRON_FECHA);
		return formateadorFecha.format(fechaActual);
	}

	private boolean obtenerEstadoValidacion() {
		return estadoValidacion.booleanValue();
	}
	
	private String hola() {
		return "hola";
	}
}
