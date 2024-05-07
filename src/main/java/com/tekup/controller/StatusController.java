package com.tekup.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/status")

public class StatusController extends BaseController{

    @PreAuthorize("hasRole('HR')")
    @RequestMapping(value="/hrping", method = RequestMethod.GET)
    public String hrPing(){
        return "Only HR Can Read This";
    }

    @PreAuthorize("hasRole('MANAGER')")
    @RequestMapping(value="/managerping", method = RequestMethod.GET)
    public String managerPing(){
        return "Any MANAGER Can Read This";
    }

    @PreAuthorize("hasRole('EMPLOYEE')")
    @RequestMapping(value="/employeeping", method = RequestMethod.GET)
    public String employeePing(){
        return "Any Employee Can Read This";
    }

}
