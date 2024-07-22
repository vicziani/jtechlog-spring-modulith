package training.mentoringapp;

import org.springframework.boot.SpringApplication;

public class TestMentoringApplication {

    public static void main(String[] args) {
        SpringApplication.from(MentoringApplication::main)
                .with(TestcontainersConfiguration.class)
                .run(args);
    }
}
