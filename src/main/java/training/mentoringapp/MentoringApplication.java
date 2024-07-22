package training.mentoringapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class MentoringApplication {

	public static void main(String[] args) {
		SpringApplication.run(MentoringApplication.class, args);
	}

}
