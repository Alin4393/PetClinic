package org.example.entity;

import jakarta.persistence.*;
import org.example.enums.PetType;

import java.util.List;

@Entity
public class Vet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int vetId;
    private String name;
    @Enumerated(value = EnumType.STRING)
    private PetType specialty;
    private int badgeId;

    public List<Appointment> getAppointment() {
        return appointment;
    }

    public void setAppointment(List<Appointment> appointment) {
        this.appointment = appointment;
    }

    @OneToMany (mappedBy = "vet")
    private List<Appointment> appointment;

    public Vet() {
    }

    public Vet(String name, PetType specialty, int badgeId) {
        this.name = name;
        this.specialty = specialty;
        this.badgeId = badgeId;
    }

    public int getVetId() {
        return vetId;
    }

    public void setVetId(int vetId) {
        this.vetId = vetId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PetType getSpecialty() {
        return specialty;
    }

    public void setSpecialty(PetType specialty) {
        this.specialty = specialty;
    }

    public int getBadgeId() {
        return badgeId;
    }

    public void setBadgeId(int badgeId) {
        this.badgeId = badgeId;
    }
}
