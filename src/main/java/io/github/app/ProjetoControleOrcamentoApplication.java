package io.github.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ProjetoControleOrcamentoApplication {

	
	
	public static void main(String[] args) {
		SpringApplication.run(ProjetoControleOrcamentoApplication.class, args);
	}

	

}
