package me.calebeoliveira.springbootpetclinic.services.map;

import me.calebeoliveira.springbootpetclinic.model.Pet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PetMapServiceTest {
    private PetMapService petMapService;
    private final Long petId = 1L;

    @BeforeEach
    void setUp() {
        petMapService = new PetMapService();
        petMapService.save(Pet.builder().id(petId).build());
    }

    @Test
    void testFindAll() {
        Set<Pet> petSet = petMapService.findAll();

        assertEquals(1, petSet.size());
    }

    @Test
    void testFindByIdExistingId() {
        Pet pet = petMapService.findById(petId);

        assertEquals(petId, pet.getId());
    }

    @Test
    void testFindByIdNotExistingId() {
        Pet pet = petMapService.findById(5L);

        assertNull(pet);
    }

    @Test
    void testFindByIdNullId() {
        Pet pet = petMapService.findById(null);

        assertNull(pet);
    }

    @Test
    void testSaveExistingId() {
        final Long id = 2L;
        Pet pet2 = Pet.builder().id(id).build();

        Pet savedPet = petMapService.save(pet2);

        assertEquals(id, savedPet.getId());
    }

    @Test
    void testSaveDuplicateId() {
        final Long id = 1L;
        Pet pet2 = Pet.builder().id(id).build();

        Pet savedPet = petMapService.save(pet2);

        assertEquals(id, savedPet.getId());
        assertEquals(1, petMapService.findAll().size());
    }

    @Test
    void testSaveNoId() {
        Pet pet2 = Pet.builder().build();

        Pet savedPet = petMapService.save(pet2);

        assertNotNull(savedPet);
        assertNotNull(savedPet.getId());
        assertEquals(2, petMapService.findAll().size());
    }

    @Test
    void testDeletePet() {
        petMapService.delete(petMapService.findById(petId));

        assertEquals(0, petMapService.findAll().size());
    }

    @Test
    void testDeleteWithWrongId() {
        Pet pet = Pet.builder().id(5L).build();

        petMapService.delete(pet);

        assertEquals(1, petMapService.findAll().size());
    }

    @Test
    void testDeleteNull() {
        petMapService.delete(null);

        assertEquals(1, petMapService.findAll().size());
    }

    @Test
    void testDeleteByIdCorrectId() {
        petMapService.deleteById(petId);

        assertEquals(0, petMapService.findAll().size());
    }

    @Test
    void testDeleteByIdWrongId() {
        petMapService.deleteById(5L);

        assertEquals(1, petMapService.findAll().size());
    }

    @Test
    void testDeleteByIdNullId() {
        petMapService.deleteById(null);

        assertEquals(1, petMapService.findAll().size());
    }
}