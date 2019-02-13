package com.lambda.dogs;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DogRepository extends JpaRepository<Dog, Long> {
//    Dog findDog ( Dog d);
    Dog findByBreed (String breed);
    boolean deleteByBreed(String breed);
}
