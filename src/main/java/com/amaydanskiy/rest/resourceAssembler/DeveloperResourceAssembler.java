package com.amaydanskiy.rest.resourceAssembler;


import com.amaydanskiy.model.Developer;
import com.amaydanskiy.resr.controller.DeveloperController;
import com.amaydanskiy.rest.resource.DeveloperResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import java.util.List;

public class DeveloperResourceAssembler extends ResourceAssemblerSupport<Developer, DeveloperResource>{

    public DeveloperResourceAssembler() {
        super(DeveloperController.class, DeveloperResource.class);
    }

    @Override
    public DeveloperResource toResource(Developer developer) {
        DeveloperResource resource =  createResourceWithId(developer.getId() ,developer);
        resource.setName(developer.getFirstName());

        return resource;
    }
}
