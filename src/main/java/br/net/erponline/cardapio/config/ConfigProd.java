package br.net.erponline.cardapio.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.net.erponline.cardapio.services.DBService;

@Configuration
@Profile("prod")
public class ConfigProd {
	@Autowired
	private DBService dbService;
	
	@Value("${spring.jpa.hibernate.dll-auto}")
	private String strategy;
	
	@Bean
	public boolean instantiateDatabase() throws ParseException {
		if (!"create".equals(strategy)) {
			return false;
		}
		
		dbService.instantiateTestDatabase();
		return true;
	}	
}