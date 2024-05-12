package me.calebeoliveira.springbootpetclinic.services.springdatajpa;

import me.calebeoliveira.springbootpetclinic.model.Pet;
import me.calebeoliveira.springbootpetclinic.model.PetType;
import me.calebeoliveira.springbootpetclinic.repositories.PetTypeRepository;
import me.calebeoliveira.springbootpetclinic.services.PetTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class PetTypeSDJPAService implements PetTypeService {
    private final PetTypeRepository petTypeRepository;

    public PetTypeSDJPAService(PetTypeRepository petTypeRepository) {
        this.petTypeRepository = petTypeRepository;
    }


    @Override
    public Set<PetType> findAll() {
        Set<PetType> petSet = new HashSet<>();
        petTypeRepository.findAll().forEach(petSet::add);
        return petSet;
    }

    @Override
    public PetType findById(Long id) {
        return petTypeRepository.findById(id).orElse(null);
    }

    @Override
    public PetType save(PetType object) {
        return petTypeRepository.save(object);
    }

    @Override
    public void delete(PetType object) {
        petTypeRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        petTypeRepository.deleteById(id);
    }
}
