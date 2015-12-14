package com.amaydanskiy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

@Controller
public class DevelopersController {
    @Autowired
    DeveloperRepository developerRepository;

    @Autowired
    SkillRepository skillRepository;

    @RequestMapping("/developer/{id}")
    public String developer(@PathVariable Long id, Model model){
        model.addAttribute("developer", developerRepository.findOne(id));
        model.addAttribute("skills", skillRepository.findAll());
        return "developer";
    }

    @RequestMapping(value = "/developers",method = RequestMethod.GET)
    public String developerList(Model model){
        model.addAttribute("developers", developerRepository.findAll());
        return "developers";
    }

    @RequestMapping(value="/developers", method = RequestMethod.POST)
    public String developersAdd(@RequestParam String email,     @RequestParam String firstName,
                                @RequestParam String lastName,  Model model){
        Developer newDeveloper = new Developer();
        newDeveloper.setEmail(email);
        newDeveloper.setFirstName(firstName);
        newDeveloper.setLastName(lastName);
        developerRepository.save(newDeveloper);

        model.addAttribute("developer", newDeveloper);
        model.addAttribute("skills", skillRepository.findAll());
        return "redirect:/developer/" + newDeveloper.getId();
    }

    @RequestMapping(value="/developer/{id}/skills", method=RequestMethod.POST)
    public String developersAddSkill(@PathVariable Long id, @RequestParam(required = false) Long skillId, Model model) {

        Skill skill;

        System.out.println(skillId);


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
            return "redirect:/developer/" + developer.getId();
        }

        model.addAttribute("developers", developerRepository.findAll());
        return "redirect:/developers";
    }

    @RequestMapping(value = "/skills")
    public String skillsAdd(@RequestParam(required = false) String label, @RequestParam(required = false) String description, Model model){
        Skill skill = new Skill();
        skill.setLabel(label);
        skill.setDescription(description);
        skillRepository.save(skill);

        model.addAttribute("skills", skillRepository.findAll());
        return "/skills";
    }
}
