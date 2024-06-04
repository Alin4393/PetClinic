package org.example.repository;

import org.example.entity.Appointment;
import org.example.entity.PetOwner;
import org.hibernate.Session;
import org.hibernate.query.Query;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Queue;

public class AppointmentRepository extends GenericRepository<Appointment> {

    public List<Appointment> findAppointmentBetweenInterval(LocalDateTime begin, LocalDateTime end){
        Session session = sessionFactory.openSession();
        Query<Appointment> appointmentsQuery = session.createQuery("select a from Appointment a where " +
                "a.dateTime > :param1 and a.dateTime < :param2");
        appointmentsQuery.setParameter("param1",begin);
        appointmentsQuery.setParameter("param2",end);
        List<Appointment> appointmentList = appointmentsQuery.getResultList();
        session.close();
        return appointmentList;
    }

    public Optional<Appointment> findByVetBadgeIdAndDateTime(String badgeId, LocalDateTime dateTime){
        Session session = sessionFactory.openSession();
        Query<Appointment> appointmentQuery = session.createQuery("select a from Appointment a where " +
                "a.vet.badgeId = :param1 and a.dateTime = :param2");
        appointmentQuery.setParameter("param1",badgeId);
        appointmentQuery.setParameter("param2",dateTime);
        Optional<Appointment> optionalAppointment = appointmentQuery.uniqueResultOptional();
        session.close();
        return  optionalAppointment;
    }


}
