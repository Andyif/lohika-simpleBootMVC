package com.amaydanskiy.controllers;

import com.amaydanskiy.model.Skill;
import com.amaydanskiy.repository.DeveloperRepository;
import com.amaydanskiy.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/skills")
public class SkillsController {

//    private DeveloperRepository developerRepository;
    private SkillRepository skillRepository;

//    @Autowired
//    public void setDeveloperRepository(DeveloperRepository developerRepository) {
//        this.developerRepository = developerRepository;
//    }

    @Autowired
    public void setSkillRepository(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String skills(Model model){
        model.addAttribute("skills", skillRepository.findAll());
        return "/skills";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addSkills(@RequestParam String label, @RequestParam String description, Model model){
        Skill newSkill = new Skill();
        newSkill.setLabel(label);
        newSkill.setDescription(description);
        skillRepository.save(newSkill);

        model.addAttribute("skills", skillRepository.findAll());
        return "/skills";
    }
}
