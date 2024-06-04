package org.example.repository;

import org.example.entity.Pet;
import org.hibernate.Session;
import org.hibernate.query.Query;


import java.util.List;
import java.util.Optional;

public class PetRepository extends GenericRepository <Pet> {


    public List<Pet> findAllPets(){
        Session session = sessionFactory.openSession();
        Query<Pet> query = session.createQuery("select p from Pet p" );
        List<Pet> petList = query.getResultList();
        session.close();
        return petList;
    }
    public Optional<Pet> findPetByCollarId(String collarId ) {
        Session session = sessionFactory.openSession();
        Query<Pet> petQuery = session.createQuery ( "select p from Pet p where p.collarId = :parameter");
        petQuery.setParameter("parameter", collarId);
        Optional<Pet> petOptional = petQuery.uniqueResultOptional();
        session.close();
        return petOptional;
    }
}
