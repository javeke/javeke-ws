package com.example.javeke.ws.portfolio.services;

import com.example.javeke.ws.portfolio.models.dao.Visitor;
import com.example.javeke.ws.portfolio.repositories.VisitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VisitorService {

    private final VisitorRepository visitorRepository;

    @Autowired
    public VisitorService(VisitorRepository visitorRepository){
        this.visitorRepository = visitorRepository;
    }

    public List<Visitor> getAllVisitor(){
        return visitorRepository.findAll();
    }

    public Visitor getVisitorByIpAddress(String ipAddress){
        return visitorRepository.findByIpAddress(ipAddress);
    }

    public Visitor addVisitor(Visitor visitor) {

        Visitor found = visitorRepository.findByIpAddress(visitor.getIpAddress());

        Visitor result;

        if(found == null){
            result = visitorRepository.save(visitor);
        }
        else {
            found.setNumVisits(found.getNumVisits()+1);
            visitorRepository.save(found);
            result = found;
        }
        return result;
    }
}
