package com.example.javeke.services.portfolio;

import com.example.javeke.model.dao.PortfolioVisitor;
import com.example.javeke.repository.portfolio.PortfolioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PortfolioService {

    private final PortfolioRepository portfolioRepository;

    @Autowired
    public PortfolioService(PortfolioRepository portfolioRepository){
        this.portfolioRepository = portfolioRepository;
    }

    public List<PortfolioVisitor> getAllVisitor(){
        return portfolioRepository.findAll();
    }

    public PortfolioVisitor addVisitor(PortfolioVisitor visitor){

        PortfolioVisitor found = portfolioRepository.findByIpAddress(visitor.getIpAddress());

        PortfolioVisitor result = new PortfolioVisitor();
        if(found == null){
            result = portfolioRepository.save(visitor);
        }

        return found;
    }
}
