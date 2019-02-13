package com.lambda.dogs;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DogRepository extends JpaRepository<Dog, Long> {
    public Dog findDog ( Dog d);
}
