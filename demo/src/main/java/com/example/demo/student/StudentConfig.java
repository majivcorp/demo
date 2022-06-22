package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

import static java.time.Month.JANUARY;

@Configuration
public class StudentConfig {

    @Bean // it injects StudentRepository interface
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {
            Student mariam = new Student(
                    1L,
                    "Mariam",
                    "mariam.jamal@gmail.com",
                    LocalDate.of(2000, JANUARY, 5)
            );

            Student andrew = new Student(
                    1L,
                    "Andrew",
                    "andrew.jamal@gmail.com",
                    LocalDate.of(2006, JANUARY, 5)
            );

            //it invokes the repository and saves/passes a list to the database
            repository.saveAll(
                    List.of(mariam, andrew)
            );
        };
    }
}
