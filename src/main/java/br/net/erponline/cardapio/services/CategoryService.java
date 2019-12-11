package br.net.erponline.cardapio.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.net.erponline.cardapio.entities.Category;
import br.net.erponline.cardapio.repositories.CategoryRepository;
import br.net.erponline.cardapio.services.exceptions.DatabaseException;
import br.net.erponline.cardapio.services.exceptions.ObjectNotFoundException;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repository;
	
	public List<Category> findAll() {
		return repository.findAll();
	}
	
	public Category findById(Long id) {
		Optional<Category> entity = repository.findById(id);
		return entity.orElseThrow(() -> new ObjectNotFoundException(id));
	}
	
	public Category insert(Category category) {
		return repository.save(category);
	}
	
	public void delete (Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ObjectNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	public Category update(Long id, Category category) {
		try {
			Category newCategory = repository.getOne(id);
			updateData(newCategory, category);
			return repository.save(newCategory);
		} catch (EntityNotFoundException e) {
			throw new ObjectNotFoundException(id);
		}
	}

	private void updateData(Category newCategory, Category category) {
		newCategory.setName(category.getName());
	}
}