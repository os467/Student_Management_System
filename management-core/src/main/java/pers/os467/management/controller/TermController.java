package pers.os467.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.os467.management.base.response.ResponseEntity;
import pers.os467.management.service.TermService;

@RestController
@RequestMapping("/term")
public class TermController {

    @Autowired
    private TermService termService;

    @GetMapping("/withinFourYears")
    public ResponseEntity getTermWithinFourYears(){
        return termService.getTermWithinFourYears();
    }

}
