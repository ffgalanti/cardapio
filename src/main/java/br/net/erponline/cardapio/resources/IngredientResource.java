package br.net.erponline.cardapio.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.net.erponline.cardapio.entities.Ingredient;
import br.net.erponline.cardapio.entities.dto.IngredientDTO;
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

	@GetMapping(value = "/page")
	public ResponseEntity<Page<IngredientDTO>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="name") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) 
	{
		Page<Ingredient> ingredients = service.findPage(page, linesPerPage, orderBy, direction);
		
		Page<IngredientDTO> ingredientsDTO = ingredients.map(ingredient -> new IngredientDTO(ingredient));
		return ResponseEntity.ok().body(ingredientsDTO);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Ingredient> findById(@PathVariable Long id) {
		Ingredient ingredient = service.findById(id);
		return ResponseEntity.ok().body(ingredient);
	}
	
	@PostMapping
	public ResponseEntity<Ingredient> insert(@RequestBody Ingredient ingredient) {
		ingredient = service.insert(ingredient);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(ingredient.getId()).toUri();
		return ResponseEntity.created(uri).body(ingredient);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Ingredient> update(@PathVariable Long id, @RequestBody Ingredient ingredient) {
		ingredient = service.update(id, ingredient);
		return ResponseEntity.ok().body(ingredient);
	}
}