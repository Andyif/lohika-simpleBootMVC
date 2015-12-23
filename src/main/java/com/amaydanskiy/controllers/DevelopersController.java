package com.amaydanskiy.controllers;

import com.amaydanskiy.model.Developer;
import com.amaydanskiy.model.Skill;
import com.amaydanskiy.repository.DeveloperRepository;
import com.amaydanskiy.repository.SkillRepository;
import com.amaydanskiy.service.PageWrapper;
import com.amaydanskiy.service.TransactionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/developers")
public class DevelopersController {
    @Autowired
    private DeveloperRepository developerRepository;

    @Autowired
    private SkillRepository skillRepository;

    @RequestMapping(method = RequestMethod.GET)
    public String developerList( Model model, @PageableDefault(page = 0, size = 2) Pageable pageable){
        PageWrapper<Developer> pageWrapper = new PageWrapper<>(developerRepository.findAll(pageable), "developers");
        model.addAttribute("developers", pageWrapper.getContent());
        model.addAttribute("page", pageWrapper);

        return "developers";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addDeveloper(@RequestParam String email,     @RequestParam String firstName,
                                @RequestParam String lastName,  Model model){
        Developer newDeveloper = new Developer();
        newDeveloper.setEmail(email);
        newDeveloper.setFirstName(firstName);
        newDeveloper.setLastName(lastName);
        developerRepository.save(newDeveloper);

        model.addAttribute("developer", newDeveloper);
        model.addAttribute("skills", skillRepository.findAll());

        return "redirect:developers/" + newDeveloper.getId();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String editDeveloper(@PathVariable Long id, Model model){
        model.addAttribute("developer", developerRepository.findOne(id));
        model.addAttribute("skills", skillRepository.findAll());
        return "developers";
    }

    @RequestMapping(value="/{id}/skills", method=RequestMethod.POST)
    public String developersAddSkill(@PathVariable Long id,
                                     @RequestParam(required = false) Long skillId, Model model) {

        Skill skill;

        if (skillId !=null){
            skill = skillRepository.findOne(skillId);
        }else {
            model.addAttribute("skills", skillRepository.findAll());
            return "redirect:/skills";
        }
        Developer developer = developerRepository.findOne(id);

        if (developer != null) {
            if (!developer.hasSkill(skill)) {
                developer.getSkills().add(skill);
                developerRepository.save(developer);
            }
            model.addAttribute("developer", developerRepository.findOne(id));
            model.addAttribute("skills", skillRepository.findAll());

            new TransactionalService().getAll(developerRepository, skillRepository);

            return "redirect:/developers/" + developer.getId();
        }

        model.addAttribute("developers", developerRepository.findAll());
        return "redirect:developers";
    }
}
