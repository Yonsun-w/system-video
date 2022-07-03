package com.neusoft.java.hars.controller;


import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/")
public class test {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {

        return "login";


    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register() {

        return "register";

    }

    @RequestMapping(value = "/hospital", method = RequestMethod.GET)
    public String hospital() {
        return "hospital/show";

    }
}
