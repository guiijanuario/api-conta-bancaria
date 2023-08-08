package br.com.catalisa.bancozup;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Banco Zup API", version = "1.0", description = "API desenvolvida para gerenciar conta do cliente do Banco Zup"))
public class BancozupApplication {

	public static void main(String[] args) {
		SpringApplication.run(BancozupApplication.class, args);
	}

}
