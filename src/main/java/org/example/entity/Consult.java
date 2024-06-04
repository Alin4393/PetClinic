package org.example.entity;

import jakarta.persistence.*;

@Entity
public class Consult {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int consultId;

    @OneToOne
    @JoinColumn
    private Appointment appointment;

    private String notes;

    public int getConsultId() {
        return consultId;
    }

    public void setConsultId(int consultId) {
        this.consultId = consultId;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
