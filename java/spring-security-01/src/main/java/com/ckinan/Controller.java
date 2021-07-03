package com.ckinan;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @RequestMapping("/protected-resource")
    public String protectedResource() {
        return "The Protected Resource";
    }

    @RequestMapping("/public-resource")
    public String publicResource() {
        return "The Public Resource";
    }

}
