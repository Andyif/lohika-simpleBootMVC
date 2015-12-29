package com.amaydanskiy.rest.controller;


import com.amaydanskiy.model.Skill;
import com.amaydanskiy.repository.SkillRepository;
import com.amaydanskiy.rest.resource.SkillResource;
import com.amaydanskiy.rest.resourceAssembler.SkillResourceAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/rest/skills")
public class SkillController {

    @Autowired
    private SkillRepository skillRepository;

    @RequestMapping(method = GET)
    public HttpEntity<List<SkillResource>> showAll(){
        Iterable<? extends Skill> skills = skillRepository.findAll();
        SkillResourceAssembler skillResourceAssembler = new SkillResourceAssembler();
        List<SkillResource> resources = skillResourceAssembler.toResources(skills);
        return new HttpEntity<List<SkillResource>>(resources);
    }

    @RequestMapping(value = "/{id}", method = GET)
    public HttpEntity<SkillResource> showDeveloper(@PathVariable Long id){
        Skill skill = skillRepository.findOne(id);
        SkillResourceAssembler skillResourceAssembler = new SkillResourceAssembler();
        SkillResource resource = skillResourceAssembler.toResource(skill);
        return new HttpEntity<SkillResource>(resource);
    }

    @RequestMapping(method = POST)
    public HttpEntity<SkillResource> creatDeveloper(@RequestBody Skill skill){
        skillRepository.save(skill);
        System.out.println(skill.getId());
        SkillResourceAssembler skillResourceAssembler = new SkillResourceAssembler();
        SkillResource resource = skillResourceAssembler.toResource(skill);
        return new HttpEntity<SkillResource>(resource);
    }
}
