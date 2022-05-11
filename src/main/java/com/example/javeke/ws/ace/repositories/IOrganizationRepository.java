package com.example.javeke.ws.ace.repositories;

import com.example.javeke.ws.ace.models.dao.Organization;
<<<<<<< HEAD
import org.springframework.data.mongodb.repository.DeleteQuery;
=======
>>>>>>> d0ad902 (implement websocket API and REST API for the organization CRUD operations)
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrganizationRepository extends MongoRepository<Organization, String> {
<<<<<<< HEAD
    Organization findByName(String name);
    Organization findByOrganizationId(String organizationId);
    int deleteByOrganizationId(String organizationId);
=======

    Organization findByName(String name);
    Organization findByOrganizationId(String organizationId);
>>>>>>> d0ad902 (implement websocket API and REST API for the organization CRUD operations)
}
