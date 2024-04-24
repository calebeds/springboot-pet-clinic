package me.calebeoliveira.springbootpetclinic.services;

import me.calebeoliveira.springbootpetclinic.model.Owner;

import java.util.Set;

public interface OwnerService extends CrudService<Owner, Long> {
    Owner findByLastName(String lastName);
}
