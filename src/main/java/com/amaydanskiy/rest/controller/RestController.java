package com.amaydanskiy.rest.controller;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping("/rest")
public class RestController {

    @RequestMapping(method = GET)
//    public HttpEntity<Resource<String>> describeResources(){
    public ResponseEntity<ResourceSupport> describeResources(){
    ResourceSupport resourceSupport = new ResourceSupport();
    resourceSupport.add(ControllerLinkBuilder.linkTo(DeveloperController.class).withRel("developers"));
    resourceSupport.add(ControllerLinkBuilder.linkTo(SkillController.class).withRel("skills"));

    return new ResponseEntity<ResourceSupport>(resourceSupport, HttpStatus.OK);

//        Resource<String> resource = new Resource<String>("REST description", ControllerLinkBuilder.linkTo(SkillController.class).withSelfRel());
//        resource.add(ControllerLinkBuilder.linkTo(DeveloperController.class).withRel("developers"));
//        resource.add(ControllerLinkBuilder.linkTo(SkillController.class).withRel("skills"));
//        return new HttpEntity<Resource<String>>(resource);
    }
}
