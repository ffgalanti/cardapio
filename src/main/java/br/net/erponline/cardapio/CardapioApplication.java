package br.net.erponline.cardapio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class CardapioApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(CardapioApplication.class, args);
	}

}
