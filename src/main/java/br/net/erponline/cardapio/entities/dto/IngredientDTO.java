package br.net.erponline.cardapio.entities.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.net.erponline.cardapio.entities.Ingredient;

public class IngredientDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long id;
	
	@NotEmpty(message = "{validation.notempty}")
	@Length(min = 3, max = 80, message = "{validation.numbercharacter}")
	private String name;

	public IngredientDTO() {

	}
	
	public IngredientDTO(Ingredient ingredient) {
		this.id = ingredient.getId();
		this.name = ingredient.getName();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}