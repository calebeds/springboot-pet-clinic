package me.calebeoliveira.springbootpetclinic.services;

import me.calebeoliveira.springbootpetclinic.model.Vet;

import java.util.Set;

public interface VetService {
    Vet findById(Long id);
    Vet save(Vet vet);
    Set<Vet> findAll();
}
