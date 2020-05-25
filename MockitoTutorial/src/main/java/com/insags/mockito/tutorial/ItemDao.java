package com.insags.mockito.tutorial;

public interface ItemDao {

	String[] obtenerTodosLosItems();

	void actualizarItem(String nuevoItem, Integer posicion);

}
