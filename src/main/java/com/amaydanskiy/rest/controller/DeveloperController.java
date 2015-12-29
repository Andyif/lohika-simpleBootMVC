package com.amaydanskiy.rest.controller;


import com.amaydanskiy.model.Developer;
import com.amaydanskiy.repository.DeveloperRepository;
import com.amaydanskiy.rest.resource.DeveloperResource;
import com.amaydanskiy.rest.resourceAssembler.DeveloperResourceAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.websocket.server.PathParam;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/rest/developers")
public class DeveloperController {

    @Autowired
    private DeveloperRepository developerRepository;

    @RequestMapping(method = GET)
    public HttpEntity<List<DeveloperResource>> showAll(){
        Iterable<? extends Developer> developers = developerRepository.findAll();
        DeveloperResourceAssembler developerResourceAssembler = new DeveloperResourceAssembler();
        List<DeveloperResource> resources = developerResourceAssembler.toResources(developers);
        return new HttpEntity<List<DeveloperResource>>(resources);
    }

    @RequestMapping(value = "/{id}", method = GET)
    public HttpEntity<DeveloperResource> showDeveloper(@PathVariable Long id){
        Developer developers = developerRepository.findOne(id);
        DeveloperResourceAssembler developerResourceAssembler = new DeveloperResourceAssembler();
        DeveloperResource resource = developerResourceAssembler.toResource(developers);
        return new HttpEntity<DeveloperResource>(resource);
    }

    @RequestMapping(method = POST)
    public HttpEntity creatDeveloper(@RequestBody Developer developer){
        Developer newDeveloper = developer;
        developerRepository.save(newDeveloper);
        System.out.println(newDeveloper.getId());
        DeveloperResourceAssembler developerResourceAssembler = new DeveloperResourceAssembler();
        DeveloperResource resource = developerResourceAssembler.toResource(newDeveloper);
        return new HttpEntity(resource);
    }
}
