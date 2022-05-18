package com.example.javeke.ws.ace.repositories;

import com.example.javeke.ws.ace.models.dao.Organization;
import org.springframework.data.mongodb.repository.DeleteQuery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrganizationRepository extends MongoRepository<Organization, String> {
    Organization findByName(String name);
    Organization findByOrganizationId(String organizationId);
    int deleteByOrganizationId(String organizationId);
}
