package br.net.erponline.cardapio.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.net.erponline.cardapio.entities.Category;
import br.net.erponline.cardapio.repositories.CategoryRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{

//	@Autowired
//	private UserRepository userRepository;
//
	@Autowired
	private CategoryRepository categoryRepository;
//
//	@Autowired
//	private IngredientRepository ingredientRepository;
//	
//	@Autowired
//	private ProductRepository productRepository; 
//
//	@Autowired
//	private OrderRepository orderRepository;
//
//	@Autowired
//	private OrderItemRepository orderItemRepository;
	
	@Override
	public void run(String... args) throws Exception {
//		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
//		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");
//		
//		userRepository.saveAll(Arrays.asList(u1, u2));
//		
		Category cat1 = new Category(null, "Lanche");
		Category cat2 = new Category(null, "Pizza");
		Category cat3 = new Category(null, "Suco");
//		
		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
//		
//		Ingredient ig1 = new Ingredient(null, "tomate");
//		Ingredient ig2 = new Ingredient(null, "orégano");
//		Ingredient ig3 = new Ingredient(null, "cebola");
//		Ingredient ig4 = new Ingredient(null, "mussarela");
//		Ingredient ig5 = new Ingredient(null, "presunto");
//
//		ingredientRepository.saveAll(Arrays.asList(ig1, ig2, ig3, ig4, ig5));
//		
//		Product p1 = new Product(null, "Pizza de mussarela", 29.5);
//		Product p2 = new Product(null, "Pizza de mussarela", 35.50);
//		Product p3 = new Product(null, "Pizza de mussarela", 19.90);
//		
//		p1.getCategories().addAll(Arrays.asList(cat1, cat2));
//		p1.getIngredients().addAll(Arrays.asList(ig1, ig2));
//		
//		p1.getCategories().addAll(Arrays.asList(cat1, cat3));
//		p1.getIngredients().addAll(Arrays.asList(ig2, ig3));
//		
//		p1.getCategories().addAll(Arrays.asList(cat1));
//		p1.getIngredients().addAll(Arrays.asList(ig1, ig2, ig3, ig4));
//			
//		productRepository.saveAll(Arrays.asList(p1, p2, p3));
//
//		Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), OrderStatus.PAID, u1);
//		Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), OrderStatus.WAITING_PAYMENT, u2);
//		Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), OrderStatus.WAITING_PAYMENT, u1);	
//		
//		orderRepository.saveAll(Arrays.asList(o1, o2, o3));
//		
//		OrderItem oi1 = new OrderItem(o1, p1, 2, p1.getPrice());
//		OrderItem oi2 = new OrderItem(o1, p3, 1, p3.getPrice());
//		OrderItem oi3 = new OrderItem(o2, p3, 2, p3.getPrice());
//		
//		orderItemRepository.saveAll(Arrays.asList(oi1, oi2, oi3));
	}
	
}