package me.calebeoliveira.springbootpetclinic.repositories;

import me.calebeoliveira.springbootpetclinic.model.Specialty;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecialtyRepository extends CrudRepository<Specialty, Long> {
}
