package com.example.javeke.portfolio.controllers;

import com.example.javeke.ws.ace.repositories.IOrganizationRepository;
import com.example.javeke.ws.portfolio.controllers.visitor.VisitorController;
import com.example.javeke.ws.portfolio.models.dao.Visitor;
import com.example.javeke.ws.portfolio.repositories.visitor.VisitorRepository;
import com.example.javeke.ws.portfolio.services.visitor.VisitorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(VisitorController.class)
public class VisitorControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private VisitorService visitorService;

    @MockBean
    private VisitorRepository visitorRepository;

    @MockBean
    private IOrganizationRepository organizationRepository;

    @Test
    public void givenNoVisitors_whenGetVisitors_returnEmptyJsonArray() throws Exception {
        List<Visitor> noVisitors = new ArrayList<>();
        given(visitorService.getAllVisitor()).willReturn(noVisitors);

        mvc.perform(get("/api/portfolio/visitors").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }


    @Test
    public void givenVisitors_whenGetVisitors_returnJsonArray() throws Exception {
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

        given(visitorService.getAllVisitor()).willReturn(visitors);

        mvc.perform(get("/api/portfolio/visitors").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].ipAddress", is(v0.getIpAddress())))
                .andExpect(jsonPath("$[0].city", is(v0.getCity())))
                .andExpect(jsonPath("$[0].state", is(v0.getState())))
                .andExpect(jsonPath("$[0].country", is(v0.getCountry())))
                .andExpect(jsonPath("$[0].numVisits", is(v0.getNumVisits())))
                .andExpect(jsonPath("$[1].ipAddress", is(v1.getIpAddress())))
                .andExpect(jsonPath("$[1].city", is(v1.getCity())))
                .andExpect(jsonPath("$[1].state", is(v1.getState())))
                .andExpect(jsonPath("$[1].country", is(v1.getCountry())))
                .andExpect(jsonPath("$[1].numVisits", is(v1.getNumVisits())));
    }


    @Test
    public void givenVisitorIpAddress_whenGetVisitor_returnVisitorAsJsonObject() throws Exception {

        String ipAddress = "10.0.0.1";

        Visitor v0 = new Visitor();
        v0.setCity("Kingston 6");
        v0.setNumVisits(1);
        v0.setIpAddress(ipAddress);
        v0.setCountry("Jamaica");


        given(visitorService.getVisitorByIpAddress(ipAddress)).willReturn(v0);

        mvc.perform(get("/api/portfolio/visitors/"+ipAddress).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ipAddress", is(v0.getIpAddress())))
                .andExpect(jsonPath("$.ipAddress", is(ipAddress)))
                .andExpect(jsonPath("$.city", is(v0.getCity())))
                .andExpect(jsonPath("$.state", is(v0.getState())))
                .andExpect(jsonPath("$.country", is(v0.getCountry())))
                .andExpect(jsonPath("$.numVisits", is(v0.getNumVisits())));
    }
}
