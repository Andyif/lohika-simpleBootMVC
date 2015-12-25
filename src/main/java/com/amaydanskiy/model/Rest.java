package com.amaydanskiy.model;


import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.hateoas.ResourceSupport;

public class Rest extends ResourceSupport{

    @JsonCreator
    public Rest(){
    }
}
