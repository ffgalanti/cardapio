package br.net.erponline.cardapio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.net.erponline.cardapio.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}