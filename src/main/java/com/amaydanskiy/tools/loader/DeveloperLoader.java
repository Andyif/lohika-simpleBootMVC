package com.amaydanskiy.tools.loader;

import com.amaydanskiy.model.Developer;
import com.amaydanskiy.model.Skill;
import com.amaydanskiy.repository.DeveloperRepository;
import com.amaydanskiy.repository.SkillRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class DeveloperLoader implements ApplicationListener<ContextRefreshedEvent>{

    @Autowired
    private DeveloperRepository developerRepository;

    @Autowired
    private SkillRepository skillRepository;

    private final Logger logger = Logger.getLogger(DeveloperLoader.class);

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        final Skill skill1 = new Skill();
        skill1.setLabel("Java");
        skillRepository.save(skill1);

        final Skill skill2 = new Skill();
        skill2.setLabel("javascript");
        skillRepository.save(skill2);

        final Skill skill3 = new Skill();
        skill3.setLabel("C++");
        skillRepository.save(skill3);

        final Skill skill4 = new Skill();
        skill4.setLabel("Python");
        skillRepository.save(skill4);

        final Developer developer1 = new Developer();
        developer1.setFirstName("Andy");
        developer1.setSkills(new ArrayList<Skill>(){{
            add(skill1);
            add(skill2);
        }});
        developerRepository.save(developer1);

        final Developer developer2 = new Developer();
        developer2.setFirstName("Jhoe");
        developer2.setSkills(new ArrayList<Skill>(){{
            add(skill2);
            add(skill3);
        }});
        developerRepository.save(developer2);

        final Developer developer3 = new Developer();
        developer3.setFirstName("Jhoe");
        developer3.setSkills(new ArrayList<Skill>(){{
            add(skill3);
            add(skill4);
        }});
        developerRepository.save(developer3);

        final Developer developer4 = new Developer();
        developer4.setFirstName("Jhoe");
        developer4.setSkills(new ArrayList<Skill>(){{
            add(skill4);
            add(skill1);
        }});
        developerRepository.save(developer4);

        Developer developer5 = new Developer();
        developer5.setFirstName("emptyDev");
        developerRepository.save(developer5);
    }
}
