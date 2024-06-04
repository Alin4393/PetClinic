package org.example.service;

import org.example.entity.Vet;
import org.example.enums.PetType;
import org.example.repository.VetRepository;

import java.util.List;

import static org.example.util.Util.generateRandomBadgeId;

public class VetService {
     private VetRepository vetRepository = new VetRepository();
     private InputOutputService inputOutputService = new InputOutputService();
   public void addNewVet() {
       inputOutputService.displayMessage("What is the vet's name?");
       String name =inputOutputService.getUserInput();

       inputOutputService.displayMessage("What is the vet's speciality?");
       String speciality = inputOutputService.getUserInput();

       Vet vet = createVet(name, speciality);
       vetRepository.save(vet);
    }

    private static Vet createVet(String name, String speciality) {
        int badgeId = generateRandomBadgeId();
        PetType petType = PetType.valueOf(speciality);
        Vet vet = new Vet(name, petType,badgeId); // trebuie sa validam conversia !
        return vet;
    }


    public void displayAllVets() {
      List<Vet> allVets = vetRepository.findAllVets();
       inputOutputService.displayVets(allVets);
    }
}
