package com.amaydanskiy.service;

import com.amaydanskiy.model.Developer;
import com.amaydanskiy.model.Skill;
import com.amaydanskiy.repository.DeveloperRepository;
import com.amaydanskiy.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TransactionalService {


    @Autowired
    private DeveloperRepository developerRepository;
    @Autowired
    private SkillRepository skillRepository;

//    @Autowired
//    public void setRepositories(DeveloperRepository developerRepository, SkillRepository skillRepository){
//        this.developerRepository = developerRepository;
//        this.skillRepository = skillRepository;
//    }

//    @Transactional
    public void getAll(DeveloperRepository developerRepository, SkillRepository skillRepository){
        Developer developer;
        List<Developer> listDev = (List<Developer>) developerRepository.findAll();
        listDev.stream().forEach(d ->{
            System.out.println(d.getFirstName());
            d.getSkills().stream().forEach(System.out::println);

        });


        developer = developerRepository.findOne(1L);
        developer.getSkills().stream().forEach(s -> s.getLabel());
        List<Skill> skillList = (List<Skill>) skillRepository.findAll();
        skillList.stream().forEach(System.out::println);
        developer.getSkills().get(0).getLabel();

        System.out.println(developer.getEmail());
        System.out.println(developer.getFirstName());
        System.out.println(developer.getId());
        System.out.println(developer.getLastName());


        System.out.println(developer.getSkills().size());

        System.out.println("developers with skill Java " + developerRepository.findBySkillsLabelOrderByIdAscAllIgnoreCase("java"));
    }
}
