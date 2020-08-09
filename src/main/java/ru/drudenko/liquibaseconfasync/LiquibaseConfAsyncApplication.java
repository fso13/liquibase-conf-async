package ru.drudenko.liquibaseconfasync;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class LiquibaseConfAsyncApplication {

    public static void main(String[] args) {
        SpringApplication.run(LiquibaseConfAsyncApplication.class, args);
    }

}
