package org.example.repository;

import org.example.entity.Vet;
import org.example.enums.PetType;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class VetRepository extends GenericRepository<Vet> {
    public List<Vet> findAllVets(){
        Session session = sessionFactory.openSession();
        Query<Vet> vetQuery = session.createQuery("select v from Vet v");
        List<Vet> vetList = vetQuery.getResultList();
        session.close();
        return vetList;
    }
    public Optional<Vet> findVetByBadgeId(String badgeId) {
        Session session = sessionFactory.openSession();
        Query<Vet> vetQuery = session.createQuery("select v from Vet v where v.badgeId = :parameter");
        vetQuery.setParameter("parameter", badgeId);
        Optional<Vet> optionalVet = vetQuery.uniqueResultOptional();
        session.close();
        return optionalVet;
    }

    public List<Vet> findVetsBySpecialty (PetType specialty) {
        Session session = sessionFactory.openSession();
        Query<Vet> vetQuery = session.createQuery("select v from Vet v where v.specialty = :parameter");
        vetQuery.setParameter("parameter", specialty);
        List<Vet> vetList = vetQuery.getResultList();
        session.close();
        return vetList;
    }
}
