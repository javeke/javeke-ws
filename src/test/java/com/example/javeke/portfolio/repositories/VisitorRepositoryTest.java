package com.example.javeke.portfolio.repositories;

import com.example.javeke.ws.portfolio.models.dao.Visitor;
import com.example.javeke.ws.portfolio.repositories.VisitorRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
class VisitorRepositoryTest {

    @Autowired
    private VisitorRepository visitorRepository;

    @AfterEach
    void clearDatabase(){
        visitorRepository.deleteAll();
    }

    @Test
    void findByIpAddress() {
        String ipAddress = "10.22.8.9";
        visitorRepository.save(new Visitor(
                null, "Kingston 7", "St. Andrew", "Jamaica", ipAddress
        ));

        Visitor found = visitorRepository.findByIpAddress(ipAddress);

        assertEquals(found.getIpAddress(), ipAddress);
    }
}