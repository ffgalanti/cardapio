package br.net.erponline.cardapio.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.net.erponline.cardapio.entities.Ingredient;
import br.net.erponline.cardapio.services.IngredientService;

@RestController
@RequestMapping(value = "/ingredients")
public class IngredientResource {
	
	@Autowired
	private IngredientService service;
	
	@GetMapping
	public ResponseEntity<List<Ingredient>> findAll() {
		List<Ingredient> ingredients = service.findAll();
		return ResponseEntity.ok().body(ingredients);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Ingredient> findById(@PathVariable Long id) {
		Ingredient ingredient = service.findById(id);
		return ResponseEntity.ok().body(ingredient);
	}
}