package br.com.nicolas.fipe;

import br.com.nicolas.fipe.Principal.Main;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FipeApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(FipeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Main main = new Main();
		main.exibeMenu();
	}
}
