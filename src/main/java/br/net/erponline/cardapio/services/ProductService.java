package br.net.erponline.cardapio.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.net.erponline.cardapio.entities.Category;
import br.net.erponline.cardapio.entities.Product;
import br.net.erponline.cardapio.repositories.CategoryRepository;
import br.net.erponline.cardapio.repositories.ProductRepository;
import br.net.erponline.cardapio.services.exceptions.DatabaseException;
import br.net.erponline.cardapio.services.exceptions.ObjectNotFoundException;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public Page<Product> search(String name, List<Long> categoriesId,Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);

		List<Category> categories = new ArrayList<>();
		if (categoriesId.size() > 0) {
			categories = categoryRepository.findAllById(categoriesId);
		} else {
			categories = categoryRepository.findAll();
		}
		return repository.findDistinctByNameContainingAndCategoriesIn(name, categories, pageRequest);
	}

	public List<Product> findAll() {
		return repository.findAll();
	}
	
	public Product findById(Long id) {
		Optional<Product> product = repository.findById(id);
		return product.orElseThrow(() -> new ObjectNotFoundException(id));
	}
	
	public Product insert(Product product) {
		return repository.save(product);
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ObjectNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	public Product update(Long id, Product product) {
		try {
			Product newProduct = repository.getOne(id);
			updateData(newProduct, product);
			return repository.save(newProduct);
		} catch (EntityNotFoundException e) {
			throw new ObjectNotFoundException(id);
		}
	}

	private void updateData(Product newProduct, Product product) {
		newProduct.setName(product.getName());
		newProduct.setPrice(product.getPrice());
		
		newProduct.getCategories().addAll(product.getCategories());
		newProduct.getIngredients().addAll(product.getIngredients());
	}
}
