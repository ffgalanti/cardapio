package br.net.erponline.cardapio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.net.erponline.cardapio.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}