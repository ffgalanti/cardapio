package br.net.erponline.cardapio.entities.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.net.erponline.cardapio.entities.Category;

public class CategoryDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@NotEmpty(message = "{validation.notempty}")
	@Length(min = 3, max = 80, message = "{validation.numbercharacter}")
	private String name;
	
	private String imageUrl;

	public CategoryDTO() {

	}

	public CategoryDTO(Category category) {
		this.id = category.getId();
		this.name = category.getName();
		this.imageUrl = category.getImageUrl();
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

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
}