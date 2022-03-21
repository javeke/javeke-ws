package com.example.javeke.portfolio.services;

import com.example.javeke.ws.portfolio.models.dao.Visitor;
import com.example.javeke.ws.portfolio.repositories.visitor.VisitorRepository;
import com.example.javeke.ws.portfolio.services.visitor.VisitorService;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class VisitorServiceTest {

    private VisitorService visitorService;

    @MockBean
    private VisitorRepository visitorRepository;

    @BeforeEach
    void setUp(){
        this.visitorService = new VisitorService(visitorRepository);
    }

    @Test
    void givenVisitors_itShouldGetAllVisitors(){
        Visitor v0 = new Visitor();
        v0.setCity("Kingston 6");
        v0.setNumVisits(1);
        v0.setIpAddress("10.0.0.1");
        v0.setCountry("Jamaica");

        Visitor v1 = new Visitor();
        v1.setCity("Kingston 5");
        v1.setNumVisits(2);
        v1.setIpAddress("10.0.0.3");
        v1.setCountry("Jamaica");

        List<Visitor> visitors = Arrays.asList(v0, v1);

        when(visitorRepository.findAll()).thenReturn(visitors);

        assertEquals(visitorService.getAllVisitor().size(), 2);
    }

    @Test
    void givenNewVisitor_itShouldAddTheVisitor(){
        Visitor v0 = new Visitor();
        v0.setCity("Kingston 6");
        v0.setIpAddress("10.0.0.1");
        v0.setCountry("Jamaica");

        when(visitorRepository.findByIpAddress(v0.getIpAddress())).thenReturn(null);
        when(visitorRepository.save(v0)).thenReturn(v0);
        Visitor returned = visitorService.addVisitor(v0);
        assertEquals(returned.getIpAddress(), v0.getIpAddress());
        assertEquals(returned.getNumVisits(), 0);
    }

    @Test
    void givenExistingVisitor_itShouldIncrementTheVisitorNumVisits(){
        Visitor v0 = new Visitor();
        v0.setCity("Kingston 6");
        v0.setIpAddress("10.0.0.1");
        v0.setCountry("Jamaica");
        v0.setNumVisits(1);

        when(visitorRepository.findByIpAddress(v0.getIpAddress())).thenReturn(v0);
        when(visitorRepository.save(v0)).thenReturn(v0);
        Visitor returned = visitorService.addVisitor(v0);
        assertEquals(returned.getIpAddress(), v0.getIpAddress());
        assertEquals(returned.getNumVisits(), 2);
    }

    @Test
    void givenValidIpAddress_itShouldReturnVisitor(){
        Visitor v0 = new Visitor();
        v0.setCity("Kingston 6");
        v0.setNumVisits(1);
        v0.setIpAddress("10.0.0.1");
        v0.setCountry("Jamaica");

        when(visitorRepository.findByIpAddress(v0.getIpAddress())).thenReturn(v0);
        Visitor found = visitorService.getVisitorByIpAddress(v0.getIpAddress());
        assertEquals(found.getIpAddress(), v0.getIpAddress());
        assertEquals(found.getNumVisits(), 1);
    }

    @Test
    void givenInvalidIpAddress_itShouldNotReturnVisitor(){
        Visitor v0 = new Visitor();
        v0.setCity("Kingston 6");
        v0.setNumVisits(1);
        v0.setIpAddress("10.0.0.1");
        v0.setCountry("Jamaica");

        when(visitorRepository.findByIpAddress("10.0.0.2")).thenReturn(null);
        assertNull(visitorService.getVisitorByIpAddress("10.0.0.2"));
    }
}
