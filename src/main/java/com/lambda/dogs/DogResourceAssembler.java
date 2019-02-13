package com.lambda.dogs;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class DogResourceAssembler implements ResourceAssembler<Dog, Resource<Dog>> {

    @Override
    public Resource<Dog> toResource(Dog dog){
        return new Resource<Dog>(dog,
                linkTo(methodOn(DogController.class).allBread()).withRel("dog"),
                linkTo(methodOn(DogController.class).allWeight()).withRel("weight"));
    }
}
