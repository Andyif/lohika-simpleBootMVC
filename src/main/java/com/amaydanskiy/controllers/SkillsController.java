package com.amaydanskiy.controllers;

import com.amaydanskiy.model.Skill;
import com.amaydanskiy.repository.SkillRepository;
import com.amaydanskiy.service.PageWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/skills")
public class SkillsController {

    @Autowired
    private SkillRepository skillRepository;

    @RequestMapping(method = RequestMethod.GET)
    public String skills(Model model, @PageableDefault(page = 0, size = 2) Pageable pageable){
        final PageRequest pageRequest = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), Sort.Direction.ASC, "label");
        PageWrapper<Skill> pageWrapper = new PageWrapper<>(skillRepository.findAll(pageRequest), "skills");
        model.addAttribute("skills", pageWrapper.getContent());
        model.addAttribute("page", pageWrapper);
        return "skills";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addSkills(@RequestParam String label, @RequestParam String description,
                            @PageableDefault(page = 0, size = 2) Pageable pageable, Model model){
        final Skill newSkill = new Skill();
        newSkill.setLabel(label);
        newSkill.setDescription(description);
        skillRepository.save(newSkill);

        PageRequest pageRequest = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), Sort.Direction.ASC, "label");
        final PageWrapper<Skill> pageWrapper = new PageWrapper<>(skillRepository.findAll(pageRequest), "skills");
        model.addAttribute("skills", pageWrapper.getContent());
        model.addAttribute("page", pageWrapper);
        return "skills";
    }
}
