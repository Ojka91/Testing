package com.insags.mockito.tutorial.impl;

import java.util.Arrays;
import java.util.List;

import com.insags.mockito.tutorial.ItemController;
import com.insags.mockito.tutorial.ItemDao;

public class ItemControllerImpl implements ItemController {

	public static final String EMPTY_STRING = "";

	private ItemDao itemDao;

	public ItemControllerImpl() {
	}

	public ItemControllerImpl(ItemDao itemDao) {
		this.itemDao = itemDao;
	}

	public ItemDao getItemDao() {
		return itemDao;
	}

	public void setItemDao(ItemDao itemDao) {
		this.itemDao = itemDao;
	}

	@Override
	public List<String> obtenerTodosLosItems() {
		return Arrays.asList(itemDao.obtenerTodosLosItems());
	}

	@Override
	public void actualizarItem(String item, Integer posicion) {
		itemDao.actualizarItem(item, posicion);
	}

	@Override
	public void resetearItemsEspecificos(Integer... posiciones) {
		for (Integer posicion : posiciones) {
			itemDao.actualizarItem(EMPTY_STRING, posicion);
		}
	}

	@Override
	public void listaItemsParaReseteo() {
		Integer posicion = 0;

		for (String item : itemDao.obtenerTodosLosItems()) {
			itemDao.actualizarItem(EMPTY_STRING, posicion++);
		}
	}
}
