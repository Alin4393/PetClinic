package org.example.service;

import org.example.entity.Appointment;
import org.example.entity.Consult;
import org.example.entity.PetOwner;
import org.example.repository.AppointmentRepository;
import org.example.repository.ConsultRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

public class ConsultService {
    private InputOutputService inputOutputService = new InputOutputService();
    private AppointmentRepository appointmentRepository = new AppointmentRepository();
    private ConsultRepository consultRepository = new ConsultRepository();

    public void createConsult() {
    inputOutputService.displayMessage("What is your badge ID ?");
    String badgeId = inputOutputService.getUserInput();

    inputOutputService.displayMessage("Please insert the appointment date: ");
    String date = inputOutputService.getUserInput();

    inputOutputService.displayMessage("Please insert the appointment time: ");
    String time = inputOutputService.getUserInput();

        LocalDate localDate = LocalDate.parse(date);
        LocalTime localTime = LocalTime.parse(time);
        LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);

        Optional<Appointment> optionalAppointment = appointmentRepository.findByVetBadgeIdAndDateTime(badgeId, localDateTime);
        if(optionalAppointment.isEmpty()){
            inputOutputService.displayMessage("There is no data matching your informations");
            return;
        }
        inputOutputService.displayMessage("What are the doctor notes?");
        String notes = inputOutputService.getUserInput();

        Appointment appointment= optionalAppointment.get();

        Consult consult = new Consult();
        consult.setAppointment(appointment);
        consult.setNotes(notes);
        consultRepository.save(consult);
    }
}
