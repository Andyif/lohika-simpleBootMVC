package com.amaydanskiy.rest.resource;


import org.springframework.hateoas.ResourceSupport;

import java.util.List;

public class SkillResource extends ResourceSupport {

    private String label;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
