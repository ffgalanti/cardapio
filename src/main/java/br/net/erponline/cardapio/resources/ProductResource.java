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

import br.net.erponline.cardapio.entities.Product;
import br.net.erponline.cardapio.entities.dto.ProductDTO;
import br.net.erponline.cardapio.resources.utils.URL;
import br.net.erponline.cardapio.services.ProductService;

@RestController
@RequestMapping(value = "/products")
public class ProductResource {

	@Autowired
	private ProductService service;
	
	@GetMapping(value = "/search")
	public ResponseEntity<Page<ProductDTO>> findPage(
			@RequestParam(value="name", defaultValue="") String name,
			@RequestParam(value="categories", defaultValue="") String categories, 
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="name") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) 
	{
		
		List<Long> categoriesIds = URL.decodeLongList(categories);
		String nameDecoded = URL.decodeParam(name);
		
		Page<Product> products = service.search(nameDecoded, categoriesIds, page, linesPerPage, orderBy, direction);
		
		Page<ProductDTO> productsDTO = products.map(product -> new ProductDTO(product));
		return ResponseEntity.ok().body(productsDTO);
	}
	
	@GetMapping
	public ResponseEntity<List<Product>> findAll() {
		List<Product> products = service.findAll();
		return ResponseEntity.ok().body(products);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Product> findById(@PathVariable Long id) {
		Product product = service.findById(id);
		return ResponseEntity.ok().body(product);
	}
	
	@PostMapping
	public ResponseEntity<Product> insert(@RequestBody Product product) {
		product = service.insert(product);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(product.getId()).toUri();
		return ResponseEntity.created(uri).body(product);
	}
	
	@DeleteMapping(value = "/{id}") 
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody Product product) {
		product = service.update(id, product);
		return ResponseEntity.ok().body(product);
	}
}