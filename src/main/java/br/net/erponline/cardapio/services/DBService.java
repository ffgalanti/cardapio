package br.net.erponline.cardapio.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.net.erponline.cardapio.entities.Category;
import br.net.erponline.cardapio.entities.Ingredient;
import br.net.erponline.cardapio.entities.Product;
import br.net.erponline.cardapio.repositories.CategoryRepository;
import br.net.erponline.cardapio.repositories.IngredientRepository;
import br.net.erponline.cardapio.repositories.ProductRepository;

@Service
public class DBService {
	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private IngredientRepository ingredientRepository;
	
	@Autowired
	private ProductRepository productRepository; 

	public void instantiateTestDatabase() {
		Category cat1 = new Category(null, "Lanche");
		Category cat2 = new Category(null, "Pizza");
		Category cat3 = new Category(null, "Suco");
		
		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
		
		Ingredient ig1 = new Ingredient(null, "tomate");
		Ingredient ig2 = new Ingredient(null, "orégano");
		Ingredient ig3 = new Ingredient(null, "cebola");
		Ingredient ig4 = new Ingredient(null, "mussarela");
		Ingredient ig5 = new Ingredient(null, "presunto");

		ingredientRepository.saveAll(Arrays.asList(ig1, ig2, ig3, ig4, ig5));
		
		Product p1 = new Product(null, "Pizza de mussarela", 29.5);
		Product p2 = new Product(null, "X-Salada", 35.50);
		Product p3 = new Product(null, "Suco de Abacaxi", 19.90);
		
		p1.getCategories().addAll(Arrays.asList(cat1, cat2));
		p1.getIngredients().addAll(Arrays.asList(ig1, ig2));
		
		p2.getCategories().addAll(Arrays.asList(cat1, cat3));
		p2.getIngredients().addAll(Arrays.asList(ig2, ig3));
		
		p3.getCategories().addAll(Arrays.asList(cat2));
		p3.getIngredients().addAll(Arrays.asList(ig1, ig2, ig3, ig4));
			
		productRepository.saveAll(Arrays.asList(p1, p2, p3));
	}
}