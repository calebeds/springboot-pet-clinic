package me.calebeoliveira.springbootpetclinic.services;

import me.calebeoliveira.springbootpetclinic.model.Owner;
import me.calebeoliveira.springbootpetclinic.model.Pet;

import java.util.Set;

public interface PetService {
    Pet findById(Long id);
    Pet save(Pet pet);
    Set<Pet> findAll();
}
