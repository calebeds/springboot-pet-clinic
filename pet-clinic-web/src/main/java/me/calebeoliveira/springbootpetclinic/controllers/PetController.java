package me.calebeoliveira.springbootpetclinic.controllers;

import me.calebeoliveira.springbootpetclinic.model.Owner;
import me.calebeoliveira.springbootpetclinic.model.Pet;
import me.calebeoliveira.springbootpetclinic.model.PetType;
import me.calebeoliveira.springbootpetclinic.services.OwnerService;
import me.calebeoliveira.springbootpetclinic.services.PetService;
import me.calebeoliveira.springbootpetclinic.services.PetTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Collection;

@Controller
@RequestMapping("/owners/{ownerId}")
public class PetController {
    private static final String VIEWS_PET_CREATE_OR_UPDATE_FORM = "pets/createOrUpdatePetForm";
    private final PetService petService;
    private final OwnerService ownerService;
    private final PetTypeService petTypeService;

    public PetController(PetService petService, OwnerService ownerService, PetTypeService petTypeService) {
        this.petService = petService;
        this.ownerService = ownerService;
        this.petTypeService = petTypeService;
    }

    @ModelAttribute("types")
    public Collection<PetType> populatePetTypes() {
        return petTypeService.findAll();
    }

    @ModelAttribute("owner")
    public Owner findOwner(@PathVariable("ownerId") long ownerId) {
        return ownerService.findById(ownerId);
    }

    @InitBinder
    public void initOwnerBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/pets/new")
    public String initCreationForm(Owner owner, Model model) {
        Pet pet = Pet.builder().build();
        owner.getPets().add(pet);
        pet.setOwner(owner);
        model.addAttribute("pet", pet);
        return VIEWS_PET_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/pets/new")
    public String processCreationForm(Owner owner, @Valid Pet pet, BindingResult result, Model model) {
        if(StringUtils.hasLength(pet.getName()) && pet.isNew() && owner.getPet(pet.getName(), false) != null) {
            result.rejectValue("name", "duplicate", "already exists");
        }
        owner.getPets().add(pet);
        pet.setOwner(owner);
        if(result.hasErrors()) {
            model.addAttribute("pet", pet);
            return VIEWS_PET_CREATE_OR_UPDATE_FORM;
        } else {
            petService.save(pet);
            return "redirect:/owners/" + owner.getId();
        }
    }

    @GetMapping("pets/{petId}/edit")
    public String initUpdateForm(@PathVariable("petId") long petId, Model model) {
        model.addAttribute("pet", this.petService.findById(petId));
        return VIEWS_PET_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/pets/{petId}/edit")
    public String processUpdateForm(@Valid Pet pet, BindingResult result, Owner owner, Model model) {
        if(result.hasErrors()) {
            pet.setOwner(owner);
            model.addAttribute("pet", pet);
            return VIEWS_PET_CREATE_OR_UPDATE_FORM;
        } else {
            owner.getPets().add(pet);
            this.petService.save(pet);
            return "redirect:/owners/" + owner.getId();
        }
    }
}
