package seoultech.startapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class StartappApplication {


	public static void main(String[] args) {
		SpringApplication.run(StartappApplication.class, args);
	}
}
