package com.lambda.dogs;

import lombok.Data;
//import lombok.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Dog {
    private  @Id
    @GeneratedValue Long id;

    private String breed;
    private Integer weight;
    private boolean indoor;
    public Dog(){

    }

    public Dog(String breed, int weight, boolean indoor) {
        this.breed = breed;
        this.weight = weight;
        this.indoor = indoor;
    }
}
