package com.lambda.dogs;

import org.apache.tomcat.util.file.ConfigurationSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static org.springframework.hateoas.core.DummyInvocationUtils.methodOn;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
@RequestMapping("/dogs")
public class DogController {
//    @Autowired
    private  DogRepository dogRepository;
    private DogResourceAssembler dogResourceAssembler;

    public DogController(DogRepository dogRepository, DogResourceAssembler dogResourceAssembler) {
        this.dogRepository = dogRepository;
        this.dogResourceAssembler = dogResourceAssembler;
    }

    @GetMapping("/breeds")
    public Resources<Resource<Dog>> allBread(){
        List<Resource<Dog>> dogs = dogRepository.findAll().stream().sorted((d1, d2) -> d1.getBread().compareToIgnoreCase(d2.getBread()))
                .map(dogResourceAssembler::toResource)
                .collect(Collectors.toList());
        return new Resources<>(dogs, linkTo(methodOn(DogController.class).allBread()).withSelfRel());
    }
    @GetMapping("/weight")
    public Resources<Resource<Dog>> allWeight(){
        List<Resource<Dog>> dogs = dogRepository.findAll().stream().sorted((d1, d2) -> d1.getWeight() - d2.getWeight()).map(dogResourceAssembler::toResource)
                .collect(Collectors.toList());
        return new Resources<>(dogs, linkTo(methodOn(DogController.class).allBread()).withSelfRel());
    }

    @GetMapping("/breeds/{breed}")
    public Dog getDogByBreed(@PathVariable(value="breed") String breed){
        return dogRepository.findDog(e -> (e.getId() == breed));
    }


}
