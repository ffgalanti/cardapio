package br.net.erponline.cardapio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.net.erponline.cardapio.entities.Ingredient;

public interface IngredientRepository extends JpaRepository<Ingredient, Long>{

}
