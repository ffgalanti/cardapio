package br.net.erponline.cardapio.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import br.net.erponline.cardapio.entities.Category;
import br.net.erponline.cardapio.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	@Transactional(readOnly = true)
	@Query("SELECT DISTINCT obj FROM Product obj INNER JOIN obj.categories cat WHERE (lower(obj.name) LIKE %:name%) AND (cat IN :categories)")
	Page<Product> findDistinctByNameContainingAndCategoriesIn(String name, List<Category> categories, Pageable pageRequest);

}