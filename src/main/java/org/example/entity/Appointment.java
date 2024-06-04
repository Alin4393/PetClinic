package org.example.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Appointment {

    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY)
    private int appointmentId ;

    private LocalDateTime dateTime ;


    @ManyToOne
    @JoinColumn
    private Vet vet;

    @ManyToOne
    @JoinColumn
    private Pet pet;

    private boolean isCanceled;

    @OneToOne (mappedBy = "appointment")
    private Consult consult;

    public Consult getConsult() { return consult; }

    public void setConsult(Consult consult) { this.consult = consult;}

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public boolean isCanceled() {
        return isCanceled;
    }

    public void setCanceled(boolean canceled) {
        isCanceled = canceled;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public Vet getVet() {
        return vet;
    }

    public void setVet(Vet vet) {
        this.vet = vet;
    }
}
