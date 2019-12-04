package br.net.erponline.cardapio.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.net.erponline.cardapio.entities.Ingredient;
import br.net.erponline.cardapio.repositories.IngredientRepository;
import br.net.erponline.cardapio.services.exceptions.DatabaseException;
import br.net.erponline.cardapio.services.exceptions.ResourceNotFoundException;

@Service
public class IngredientService {

	@Autowired
	private IngredientRepository repository;
	
	public List<Ingredient> findAll() {
		return repository.findAll();
	}
	
	public Ingredient findById(Long id) {
		Optional<Ingredient> ingredient = repository.findById(id);
		return ingredient.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public Ingredient insert(Ingredient ingredient) {
		return repository.save(ingredient);
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	public Ingredient update(Long id, Ingredient ingredient) {
		try {
			Ingredient newIngredient = repository.getOne(id);
			updateData(newIngredient, ingredient);
			return repository.save(newIngredient);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(Ingredient newIngredient, Ingredient ingredient) {
		newIngredient.setName(ingredient.getName());
	}
}