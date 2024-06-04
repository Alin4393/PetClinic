package org.example.repository;

import org.example.entity.PetOwner;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class PetOwnerRepository extends GenericRepository<PetOwner> {

    public List<PetOwner> findAllPetOwner(){
       Session session = sessionFactory.openSession();
        Query<PetOwner> petOwnerQuery = session.createQuery("select p from PetOwner p");
        List<PetOwner> petOwnerList = petOwnerQuery.getResultList();
        session.close();
        return petOwnerList;
    }

    public static Optional<PetOwner> findPetOwnerByPhoneNumber(String phoneNumber){
        Session session = sessionFactory.openSession();
        Query<PetOwner> petOwnerQuery = session.createQuery("select p from PetOwner p where p.phoneNumber = :parameter");
        petOwnerQuery.setParameter("parameter", phoneNumber );
        Optional<PetOwner> optionalPetOwner = petOwnerQuery.uniqueResultOptional();
        session.close();
        return optionalPetOwner;
    }
}
