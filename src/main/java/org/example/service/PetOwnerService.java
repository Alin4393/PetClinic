package org.example.service;

import org.example.entity.PetOwner;
import org.example.repository.PetOwnerRepository;

import java.util.List;

public class PetOwnerService {
    private PetOwnerRepository petOwnerRepository = new PetOwnerRepository();
    private InputOutputService inputOutputService = new InputOutputService();
    public void addNewPetOwner() {
        inputOutputService.displayMessage("what is the name of the petowner?");
        String name = inputOutputService.getUserInput();

        inputOutputService.displayMessage("what is phone number?");
        String phoneNumber = inputOutputService.getUserInput();

        PetOwner petOwner = createPetOwner(name, phoneNumber);

        petOwnerRepository.save(petOwner);

    }

    private static PetOwner createPetOwner(String name, String phoneNumber) {
        PetOwner petOwner = new PetOwner();
        petOwner.setName(name);
        petOwner.setPhoneNumber(phoneNumber);
        return petOwner;
    }

    public void displayAllPetOwner(){
        List<PetOwner> petOwnerList = petOwnerRepository.findAllPetOwner();
        inputOutputService.displayPetOwner(petOwnerList);
        System.out.println();
    }
}
