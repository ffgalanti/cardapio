package br.net.erponline.cardapio.services.exceptions;

import java.util.ResourceBundle;

import br.net.erponline.cardapio.utils.Translators;

public class ObjectNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	static ResourceBundle resourceBundle = new Translators().getIdioma();

	public ObjectNotFoundException(Object id) {
		super(resourceBundle.getString("error.NotFound") + " " + id);
	}
}