package com.amaydanskiy.controllers;

import com.amaydanskiy.model.Developer;
import com.amaydanskiy.model.Skill;
import com.amaydanskiy.repository.DeveloperRepository;
import com.amaydanskiy.repository.SkillRepository;
import com.amaydanskiy.service.PageWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import javax.websocket.server.PathParam;

@Controller
@RequestMapping("/developers")
public class DevelopersController {
    @Autowired
    DeveloperRepository developerRepository;

    @Autowired
    SkillRepository skillRepository;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String editDeveloper(@PathVariable Long id, Model model){
        model.addAttribute("developer", developerRepository.findOne(id));
        model.addAttribute("skills", skillRepository.findAll());
        return "developers";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String developerList( Model model, @PageableDefault(page = 0, size = 2) Pageable pageable){
        if (pageable != null){
            System.out.println("===> " + pageable.getOffset() +"-"+ pageable.getPageNumber() + "-"+ pageable.getPageSize() + "-"+ pageable.getSort() + " <===");
            PageWrapper<Developer> pageWrapper = new PageWrapper<>(developerRepository.findAll(pageable), "developers");
            model.addAttribute("developers", pageWrapper.getContent());
            model.addAttribute("page", pageWrapper);
        }else {
//            model.addAttribute("developers", developerRepository.findAll());
        }
//        System.out.println(pageable);
//        Pageable pageable1 = new PageRequest(0, 2);
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
        return "redirect:/developers/" + newDeveloper.getId();
    }

    @RequestMapping(value="/{id}/skills", method=RequestMethod.POST)
    public String developersAddSkill(@PathVariable Long id, @RequestParam(required = false) Long skillId, Model model) {

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
            }
            developerRepository.save(developer);
            model.addAttribute("developer", developerRepository.findOne(id));
            model.addAttribute("skills", skillRepository.findAll());
            return "redirect:/developers/" + developer.getId();
        }

        model.addAttribute("developers", developerRepository.findAll());
        return "redirect:/developers";
    }
}
