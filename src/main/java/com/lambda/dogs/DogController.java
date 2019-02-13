package com.lambda.dogs;

import org.apache.tomcat.util.file.ConfigurationSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.*;

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
    public Resources<Resource<Dog>> allBreed(){
        List<Resource<Dog>> dogs = dogRepository.findAll().stream()
                .map(dogResourceAssembler::toResource)
                .collect(Collectors.toList());
        return new Resources<>(dogs, linkTo(methodOn(DogController.class).allBreed()).withSelfRel());
    }
    @GetMapping("/weight")
    public Resources<Resource<Dog>> allWeight(){
        List<Resource<Dog>> dogs = dogRepository.findAll().stream().sorted((d1, d2) -> d1.getWeight() - d2.getWeight()).map(dogResourceAssembler::toResource)
                .collect(Collectors.toList());
        return new Resources<>(dogs, linkTo(methodOn(DogController.class).allBreed()).withSelfRel());
    }

    @GetMapping("/breeds/{breed}")
    public Dog getDogByBreed(@PathVariable String breed){
        return dogRepository.findByBreed(breed);
    }

    @PostMapping("/")
    public Dog addDog(@RequestBody Dog dog){
        dogRepository.save(dog);
        return dogRepository.findByBreed(dog.getBreed());
    }
    @PutMapping("/{id}")
    public Dog editDog(@PathVariable final Long id, @RequestBody Dog dog){
        Dog editDog = dogRepository.findById(id)
                .orElseThrow(()-> new DogNotFoundException(id));
        editDog.setBreed(dog.getBreed());
        editDog.setWeight(dog.getWeight());
        editDog.setIndoor(dog.isIndoor());
        dogRepository.save(editDog);
        return dogRepository.findById(id) .orElseThrow(()-> new DogNotFoundException(id));
    }

    @DeleteMapping("/{id}")
    public Long deleteDog(@PathVariable final Long id){
        dogRepository.deleteById(id);
        return id;
    }
    @DeleteMapping("/breeds/{breed}")
    public boolean deleteByBreed(@PathVariable String breed){
        return dogRepository.deleteByBreed(breed);
    }
}
