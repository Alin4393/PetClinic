package org.example.service;

import org.example.entity.Appointment;
import org.example.entity.Pet;
import org.example.entity.PetOwner;
import org.example.entity.Vet;

import java.util.List;
import java.util.Scanner;

public class InputOutputService {
    public String getUserInput() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        return input;
    }

    public void displayMainMenu() {
        System.out.println("Welcome to Pet Clinic");
        System.out.println("Please choose one of the following options:");
        System.out.println("1.Add Vet");
        System.out.println("2.Display all Vets");
        System.out.println("3.Add new pet-owner");
        System.out.println("4.Display all pet-owners");
        System.out.println("5. Add new Pet, please!");
        System.out.println("6. Display all pets ");
        System.out.println("7. Create appointment ");
        System.out.println("8. Create consult ");
        System.out.println("0.Exit");
    }
        public void displayMessage (String message){
            System.out.println(message);
        }

        public void displayVets (List < Vet > allVets) {
            System.out.println("This are all vets:");
            for (Vet vet : allVets) {
                System.out.println(vet.getName() + " " + vet.getSpecialty() + " " + vet.getBadgeId());
            }
            System.out.println();
        }
        public void displayPetOwner (List < PetOwner > allPetOwners) {
            System.out.println("This are all the pet owners :");
            for (PetOwner petOwner : allPetOwners) {
                System.out.println(petOwner.getName() + " " + petOwner.getPhoneNumber());
            }

        }

        public void displayAllPets (List < Pet > petList) {
            System.out.println("Those are all the pets:");
            for (Pet pet : petList) {
                System.out.println(pet.getName() + " " + pet.getPetType() + " " + pet.getPetOwner().getName());
            }
            System.out.println();
        }
    public void displayEligibleVets(List <Vet> allVets) {
        System.out.println("Please insert the number of the vet you choose:");
        for (int index = 0; index< allVets.size(); index++) {
            Vet vet = allVets.get(index);
            System.out.println((index+1) + " " + vet.getName() + " " + vet.getSpecialty() + " " + vet.getBadgeId());
        }
        System.out.println();
    }


    public void displayAppointmentMadeMessage(Appointment appointment) {
        System.out.println("Congratulations! " + appointment.getPet().getName() + " is scheduled to mett Dr. " +
                appointment.getVet().getName() + " on " + appointment.getDateTime().toLocalDate() + " at " +
                appointment.getDateTime().toLocalDate());
        System.out.println();
    }
}
