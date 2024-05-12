package me.calebeoliveira.springbootpetclinic.services.springdatajpa;

import me.calebeoliveira.springbootpetclinic.model.Specialty;
import me.calebeoliveira.springbootpetclinic.repositories.SpecialtyRepository;
import me.calebeoliveira.springbootpetclinic.services.SpecialtyService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class SpecialtySDJPAService implements SpecialtyService {
    private final SpecialtyRepository specialtyRepository;

    public SpecialtySDJPAService(SpecialtyRepository specialtyRepository) {
        this.specialtyRepository = specialtyRepository;
    }

    @Override
    public Set<Specialty> findAll() {
        Set<Specialty> specialtiesSet = new HashSet<>();
        specialtyRepository.findAll().forEach(specialtiesSet::add);
        return specialtiesSet;
    }

    @Override
    public Specialty findById(Long id) {
        return specialtyRepository.findById(id).orElse(null);
    }

    @Override
    public Specialty save(Specialty object) {
        return specialtyRepository.save(object);
    }

    @Override
    public void delete(Specialty object) {
        specialtyRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        specialtyRepository.deleteById(id);
    }
}
