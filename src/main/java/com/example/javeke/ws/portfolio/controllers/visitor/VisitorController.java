package com.example.javeke.ws.portfolio.controllers.visitor;

import com.example.javeke.ws.portfolio.models.dao.Visitor;
import com.example.javeke.ws.portfolio.services.visitor.VisitorService;
import com.example.javeke.ws.portfolio.models.requests.VisitorRequest;
import com.example.javeke.ws.portfolio.models.dto.VisitorDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/portfolio")
public class VisitorController {

    public final VisitorService visitorService;

    @Autowired
    public VisitorController(VisitorService visitorService){
        this.visitorService = visitorService;
    }

    @GetMapping("/visitors")
    public ResponseEntity<List<VisitorDto>> getVisitors(){

        List<Visitor> visitors = visitorService.getAllVisitor();

        if(visitors.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<VisitorDto> visitorDtos =  visitors.stream().map(VisitorDto::new).collect(Collectors.toList());
        return new ResponseEntity<>(visitorDtos, HttpStatus.OK);
    }

    @GetMapping("/visitors/{ipAddress}")
    public ResponseEntity<VisitorDto> getVisitorsByIpAddress(@PathVariable("ipAddress") String ipAddress){
        Visitor visitor = visitorService.getVisitorByIpAddress(ipAddress);
        if( visitor == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        VisitorDto response = new VisitorDto();
        BeanUtils.copyProperties(visitor, response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/visitors")
    public ResponseEntity<VisitorDto> addVisitor(@RequestBody VisitorRequest visitorRequest) {

        VisitorDto response = new VisitorDto();
        try {
            Visitor portfolioVisitor = new Visitor();
            BeanUtils.copyProperties(visitorRequest, portfolioVisitor);
            Visitor result = visitorService.addVisitor(portfolioVisitor);
            BeanUtils.copyProperties(result, response);
        }
        catch (Exception exception){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
