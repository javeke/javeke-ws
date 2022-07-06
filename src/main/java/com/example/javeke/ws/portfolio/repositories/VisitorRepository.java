package com.example.javeke.ws.portfolio.repositories;

import com.example.javeke.ws.portfolio.models.dao.Visitor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitorRepository extends MongoRepository<Visitor, String> {

    Visitor findByIpAddress(String ipAddress);
}
