package me.calebeoliveira.springbootpetclinic.repositories;

import me.calebeoliveira.springbootpetclinic.model.PetType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PetTypeRepository extends CrudRepository<PetType, Long> {
}
