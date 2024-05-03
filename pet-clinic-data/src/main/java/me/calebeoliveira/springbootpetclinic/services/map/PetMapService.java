package me.calebeoliveira.springbootpetclinic.services.map;

import me.calebeoliveira.springbootpetclinic.model.Pet;
import me.calebeoliveira.springbootpetclinic.services.PetService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service

public class PetMapService extends AbstractMapService<Pet, Long> implements PetService {
    @Override
    public Set<Pet> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Pet object) {
        super.delete(object);
    }

    @Override
    public Pet save(Pet object) {
        return super.save(object);
    }

    @Override
    public Pet findById(Long id) {
        return super.findById(id);
    }
}
