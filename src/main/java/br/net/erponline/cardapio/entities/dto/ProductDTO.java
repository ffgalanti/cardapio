package br.net.erponline.cardapio.entities.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.net.erponline.cardapio.entities.Ingredient;
import br.net.erponline.cardapio.entities.Product;

public class ProductDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long id;
	
	@NotEmpty(message = "{validation.notempty}")
	@Length(min = 3, max = 80, message = "{validation.numbercharacter}")
	private String name;

	@DecimalMin(value = "0.0", inclusive = false, message = "{validation.value}")
	private Double price;

	private String imageUrl;

	private Set<Ingredient> ingredients = new HashSet<>();

	public ProductDTO() {
		
	}

	public ProductDTO(Product product) {
		this.id = product.getId();
		this.name = product.getName();
		this.price = product.getPrice();
		this.imageUrl = product.getImageUrl();
		this.ingredients = product.getIngredients();
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

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Set<Ingredient> getIngredients() {
		return ingredients;
	}
}