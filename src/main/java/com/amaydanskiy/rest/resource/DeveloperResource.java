package com.amaydanskiy.rest.resource;


import com.amaydanskiy.model.Skill;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;

public class DeveloperResource extends ResourceSupport {

    private String name;
    private String lastName;
    private String email;
    private List<Skill> skills;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }
}
