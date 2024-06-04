package org.example.service;

import org.example.entity.Pet;
import org.example.entity.PetOwner;
import org.example.enums.PetType;
import org.example.repository.PetOwnerRepository;
import org.example.repository.PetRepository;
import org.example.util.Util;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class PetService {
    private InputOutputService inputOutputService = new InputOutputService();

    private PetOwnerRepository petOwnerRepository = new PetOwnerRepository();

    private PetRepository petRepository = new PetRepository();

    public void addNewPet() {
        inputOutputService.displayMessage("What is the name of pet ?");
        String petName = inputOutputService.getUserInput();
        inputOutputService.displayMessage("Please choose one of the following pet types: " + Arrays.toString(PetType.values()));
        String petType = inputOutputService.getUserInput();
        inputOutputService.displayMessage("What is the pet owner's of phone number ");
        String petOwnerPhoneNumber = inputOutputService.getUserInput();


        Optional<PetOwner> optionalPetOwner = PetOwnerRepository.findPetOwnerByPhoneNumber(petOwnerPhoneNumber);
        if (optionalPetOwner.isEmpty()) {
            inputOutputService.displayMessage(" This number phone isn't associated with any pet owner");
            return;
        }
        Pet pet = createPet(petName, petType, optionalPetOwner.get());
        petRepository.save(pet);
    }

    private Pet createPet(String petName, String petType, PetOwner petOwner) {
        String collorId = Util.generateRandomCollorId();
        Pet pet = new Pet();
        pet.setName(petName);
        pet.setPetType(PetType.valueOf(petType));
        pet.setCollarId(collorId);
        pet.setPetOwner(petOwner);
        return pet;
    }

    public void displayAllPets() {
        List<Pet> petList = petRepository.findAllPets();
        inputOutputService.displayAllPets(petList);
    }
}
