package com.example.javeke.repository.portfolio;

import com.example.javeke.model.dao.PortfolioVisitor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PortfolioRepository extends MongoRepository<PortfolioVisitor, String> {

    PortfolioVisitor findByIpAddress(String ipAddress);
}
