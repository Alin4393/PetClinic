package org.example;

import org.example.service.*;

public class PetClinic {

    private InputOutputService inputOutputService = new InputOutputService();
    private VetService vetService = new VetService();
    private PetOwnerService petOwnerService = new PetOwnerService();
    private PetService petService = new PetService();
    private AppointmentService appointmentService = new AppointmentService();
    private ConsultService consultService = new ConsultService();

    public void start() {
        while (true) {
            //pasul 1 afiseaza meniu principal
            inputOutputService.displayMainMenu();
            //pasul 2 preia input de la utilizator
            String userInput = inputOutputService.getUserInput();
            if(userInput.equals("0")){
                break;
            }
            //pasul 3 proceseaza comanda
            process(userInput);
        }
    }

    private void process(String userInput) {
        switch (userInput){
            case "1":{
                vetService.addNewVet();
                break;
            }
            case "2":{
                vetService.displayAllVets();
                break;
            }
            case "3":{
                petOwnerService.addNewPetOwner();
                break;
            }
            case"4":{
                petOwnerService.displayAllPetOwner();
                break;
            }
            case"5":{
                petService.addNewPet();
                break;
            }
            case"6":{
                petService.displayAllPets();
                break;
            }
            case "7":{
                appointmentService.createAppointment();
                break;
            }
            case "8":{
                consultService.createConsult();
                break;
            }
        }

    }

}
