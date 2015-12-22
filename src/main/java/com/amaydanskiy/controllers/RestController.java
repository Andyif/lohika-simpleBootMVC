package com.amaydanskiy.controllers;

import com.amaydanskiy.model.Rest;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;


@Controller
public class RestController {

    @RequestMapping("/rest")
    @ResponseBody
    public HttpEntity<Rest> rest(){

        final Rest rest = new Rest();
        rest.add(linkTo(methodOn(RestController.class).rest()).withSelfRel());
        rest.add(linkTo(DevelopersController.class).withRel("developers"));
        rest.add(linkTo(DevelopersController.class).withRel("skills"));

        return new ResponseEntity<>(rest, HttpStatus.OK);
    }
}
