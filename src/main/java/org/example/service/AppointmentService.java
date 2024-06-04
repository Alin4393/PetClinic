package org.example.service;

import org.example.entity.Appointment;
import org.example.entity.Pet;
import org.example.entity.Vet;
import org.example.enums.PetType;
import org.example.repository.AppointmentRepository;
import org.example.repository.PetRepository;
import org.example.repository.VetRepository;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public class AppointmentService {

    private InputOutputService inputOutputService = new InputOutputService();
    private PetRepository petRepository = new PetRepository();
    private VetRepository vetRepository = new VetRepository();
    private AppointmentRepository appointmentRepository = new AppointmentRepository();

    public void createAppointment() {
        Optional<Pet> optionalPet = getOptionalPet();
        if (optionalPet.isEmpty()) {
            inputOutputService.displayMessage("Invalid collar Id!");
            System.out.println();
            return;
        }

        Pet pet = optionalPet.get();
        Optional<Vet> optionalVet = getOptionalVet(pet.getPetType());
        if(optionalVet.isEmpty()){
            return;
        }
        Vet vet = optionalVet.get();

        LocalDate date = getAppointmentDate();
        LocalTime time = getAppointmentTime();

        if(conflictingAppointment(date,time)){
            inputOutputService.displayMessage("Sorry the doctor is not available at that time!");
            return;
        }

        Appointment appointment = buildAppointment(date, time, pet, vet);
        appointmentRepository.save(appointment);
        inputOutputService.displayAppointmentMadeMessage(appointment);


    }

    private boolean conflictingAppointment(LocalDate date, LocalTime time) {
        LocalDateTime dateTime = LocalDateTime.of(date, time);
        LocalDateTime begin = dateTime.minusMinutes(30);
        LocalDateTime end = dateTime.plusMinutes(30);
        List<Appointment> appointmentList = appointmentRepository.findAppointmentBetweenInterval(begin, end);

        if(appointmentList.isEmpty()){
            return false;
        }
        return true;
        // return !appointmentList.isEmpty(); >>> Linia 61-64
    }

    private Appointment buildAppointment(LocalDate date, LocalTime time, Pet pet, Vet vet) {
        LocalDateTime dateTime = LocalDateTime.of(date, time);

        Appointment appointment = new Appointment();
        appointment.setPet(pet);
        appointment.setVet(vet);
        appointment.setDateTime(dateTime);
        return appointment;
    }

    private LocalTime getAppointmentTime() {
        inputOutputService.displayMessage("What time do you prefer for appointment? (HH:MM)");
        String timeAsString = inputOutputService.getUserInput();
        LocalTime time = LocalTime.parse(timeAsString);
        return time;
    }

    private LocalDate getAppointmentDate() {
        inputOutputService.displayMessage("What is the date of appointment? (YYYY-MM-DD)");
        String dateAsString = inputOutputService.getUserInput();
        LocalDate date = LocalDate.parse(dateAsString);
        return date;
    }

    private Optional<Pet> getOptionalPet() {
        inputOutputService.displayMessage("What is the collarId of your pet ? ");
        String collarId = inputOutputService.getUserInput();
        Optional<Pet> optionalPet = petRepository.findPetByCollarId(collarId);
        return optionalPet;
    }

    private Optional<Vet> getOptionalVet(PetType petType){
        List<Vet> vetList = vetRepository.findVetsBySpecialty(petType);
        if (vetList.isEmpty()) {
            inputOutputService.displayMessage("Sorry! We don't have a vet available for your pet!");
            System.out.println();
            return Optional.empty();
        }
        inputOutputService.displayEligibleVets(vetList);
        String vetNumber = inputOutputService.getUserInput();
        Vet vet = vetList.get(Integer.parseInt(vetNumber)-1);
        return Optional.of(vet);
    }
}
