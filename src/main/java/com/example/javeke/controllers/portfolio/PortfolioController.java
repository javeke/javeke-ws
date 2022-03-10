package com.example.javeke.controllers.portfolio;

import com.example.javeke.model.dao.PortfolioVisitor;
import com.example.javeke.services.portfolio.PortfolioService;
import com.example.javeke.services.portfolio.models.request.PortfolioVisitorRequest;
import com.example.javeke.services.portfolio.models.response.PortfolioVisitorResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/portfolio")
public class PortfolioController {

    public final PortfolioService portfolioService;

    @Autowired
    public PortfolioController(PortfolioService portfolioService){
        this.portfolioService = portfolioService;
    }

    @GetMapping("/visitors")
    public List<PortfolioVisitor> getVisitors(){
        return portfolioService.getAllVisitor();
    }

    @PostMapping("/visitors")
    public PortfolioVisitorResponse addVisitor(@RequestBody PortfolioVisitorRequest visitorRequest){

        System.out.println(visitorRequest);

        PortfolioVisitor portfolioVisitor = new PortfolioVisitor();

        BeanUtils.copyProperties(visitorRequest, portfolioVisitor);

        PortfolioVisitor result = portfolioService.addVisitor(portfolioVisitor);

        PortfolioVisitorResponse response = new PortfolioVisitorResponse();
        BeanUtils.copyProperties(result, response);

        return  response;
    }
}
