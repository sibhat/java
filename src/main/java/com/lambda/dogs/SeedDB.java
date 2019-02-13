package com.lambda.dogs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j //Lombok auto creates Slf4j-based logs
@Configuration
public class SeedDB {
    @Bean
    public CommandLineRunner initDB(DogRepository dogrepos){
        return args -> {
            log.info("Seeding" + dogrepos.save(new Dog("Springer", 50, false)));
            log.info("Seeding" + dogrepos.save(new Dog("Bulldog", 50, true)));
            log.info("Seeding" + dogrepos.save(new Dog("Collie", 50, false)));
            log.info("Seeding" + dogrepos.save(new Dog("Boston Terrie", 35, true)));
            log.info("Seeding" + dogrepos.save(new Dog("Corgie", 35, true)));
        };
    }

}
