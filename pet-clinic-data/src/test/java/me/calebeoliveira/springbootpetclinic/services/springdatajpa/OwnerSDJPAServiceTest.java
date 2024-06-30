package me.calebeoliveira.springbootpetclinic.services.springdatajpa;

import me.calebeoliveira.springbootpetclinic.model.Owner;
import me.calebeoliveira.springbootpetclinic.repositories.OwnerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OwnerSDJPAServiceTest {

    @Mock
    OwnerRepository ownerRepository;
    @InjectMocks
    OwnerSDJPAService ownerSDJPAService;

    private final String LAST_NAME = "Smith";
    private final Owner returnOwner = Owner.builder().id(1L).lastName(LAST_NAME).build();

    @Test
    void testFindByLastName() {
        when(ownerRepository.findByLastName(anyString())).thenReturn(returnOwner);

        Owner smith = ownerSDJPAService.findByLastName(LAST_NAME);

        assertEquals(LAST_NAME, smith.getLastName());
        verify(ownerRepository).findByLastName(eq(LAST_NAME));
    }

    @Test
    void testFindAll() {
        Set<Owner> returnOwnersSet = new HashSet<>();
        returnOwnersSet.add(Owner.builder().id(1L).build());
        returnOwnersSet.add(Owner.builder().id(2L).build());

        when(ownerRepository.findAll()).thenReturn(returnOwnersSet);

        Set<Owner> owners = ownerSDJPAService.findAll();

        final int EXPECTED_SIZE = 2;

        assertNotNull(owners);
        assertEquals(EXPECTED_SIZE, owners.size());
    }

    @Test
    void testFindById() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.of(returnOwner));

        Owner owner = ownerSDJPAService.findById(1L);

        assertNotNull(owner);
        assertEquals(Optional.of(1L).get(), owner.getId());
    }

    @Test
    void testFindByIdNotFound() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.empty());

        Owner owner = ownerSDJPAService.findById(1L);

        assertNull(owner);
    }

    @Test
    void testSave() {
        Owner ownerToSave = Owner.builder().id(1L).build();

        when(ownerRepository.save(any(Owner.class))).thenReturn(returnOwner);

        Owner savedOwner = ownerSDJPAService.save(ownerToSave);

        assertNotNull(savedOwner);
        assertEquals(Optional.of(1L).get(), savedOwner.getId());
        verify(ownerRepository, times(1)).save(eq(ownerToSave));
    }

    @Test
    void testDelete() {
        ownerSDJPAService.delete(returnOwner);

        verify(ownerRepository).delete(eq(returnOwner));
    }

    @Test
    void testDeleteById() {
        ownerSDJPAService.deleteById(1L);

        verify(ownerRepository, times(1)).deleteById(1L);
    }
}