package com.insags.mockito.tutorial.impl;

import com.insags.mockito.tutorial.ItemDao;

public class ItemDaoImpl implements ItemDao {
	private String[] items ;
	
	public ItemDaoImpl(){
		items = new String[]{ "Balón", "Maceta", "Lupa" };
	}

	@Override
	public String[] obtenerTodosLosItems() {
		return items;
	}

	@Override
	public void actualizarItem(String nuevoItem, Integer posicion) {
		items[posicion] = nuevoItem;
	}
}
