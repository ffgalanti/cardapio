package br.net.erponline.cardapio.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.net.erponline.cardapio.entities.Ingredient;
import br.net.erponline.cardapio.repositories.IngredientRepository;

@Service
public class IngredientService {

	@Autowired
	private IngredientRepository repository;
	
	public List<Ingredient> findAll() {
		return repository.findAll();
	}
	
	public Ingredient findById(Long id) {
		Optional<Ingredient> ingredient = repository.findById(id);
		return ingredient.orElse(null);
	}
}
