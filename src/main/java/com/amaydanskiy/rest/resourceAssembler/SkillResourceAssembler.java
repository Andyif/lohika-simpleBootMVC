package com.amaydanskiy.rest.resourceAssembler;


import com.amaydanskiy.model.Skill;
import com.amaydanskiy.rest.controller.SkillController;
import com.amaydanskiy.rest.resource.SkillResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

public class SkillResourceAssembler extends ResourceAssemblerSupport<Skill, SkillResource>{

    public SkillResourceAssembler() {
        super(SkillController.class, SkillResource.class);
    }

    @Override
    public SkillResource toResource(Skill skill) {
        SkillResource resource =  createResourceWithId(skill.getId() ,skill);
        resource.setLabel(skill.getLabel());
        resource.setDescription(skill.getDescription());

        return resource;
    }

}
