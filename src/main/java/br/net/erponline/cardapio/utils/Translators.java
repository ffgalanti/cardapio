package br.net.erponline.cardapio.utils;

import java.util.Locale;
import java.util.ResourceBundle;

public class Translators {
	private final String MESSAGES = "messages";

	private String language;
	private String country;

	public Translators() {
		this.language = "pt";
		this.country = "BR";
	}

	public Translators(String language, String country) {
		this.language = language;
		this.country = country;
	}

	public ResourceBundle getIdioma() {
		ResourceBundle resourceBundle = ResourceBundle.getBundle(MESSAGES, new Locale(this.language, this.country));
		return resourceBundle;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "Translator [MESSAGES=" + MESSAGES + ", language=" + language + ", country=" + country + "]";
	}
}