package com.amaydanskiy.integration;

import com.amaydanskiy.model.Developer;
import com.amaydanskiy.repository.DeveloperRepository;
import com.amaydanskiy.model.Skill;
import com.amaydanskiy.repository.SkillRepository;
import com.amaydanskiy.integration.configuration.RepositoryConfiguration;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {RepositoryConfiguration.class})
public class DeveloperRepositoryIT {

    private DeveloperRepository developerRepository;
    private SkillRepository skillRepository;

    @Autowired
    public void setDeveloperRepository(DeveloperRepository developerRepository){
        this.developerRepository = developerRepository;
    }

    @Autowired
    public void setSkillRepository(SkillRepository skillRepository){
        this.skillRepository = skillRepository;
    }

    @Test
    public void testCRUDDeveloper(){
        Skill skill1 = new Skill();
        skill1.setLabel("test1label");
        skill1.setDescription("tst1Description");
        skillRepository.save(skill1);

        Skill skill2 = new Skill();
        skill2.setLabel("test2label");
        skill2.setDescription("tst2Description");
        skillRepository.save(skill2);

        Developer developer = new Developer();

        developer.setEmail("test@mail.com");
        developer.setFirstName("testFirstName");
        developer.setLastName("testLastName");
        developer.setSkills(new ArrayList<Skill>(){{
            add(skill1);
            add(skill2);
        }});

        Assert.assertNull(developer.getId());
        developerRepository.save(developer);
        Assert.assertNotNull(developer.getId());

        Developer fetchedDeveloper = developerRepository.findOne(developer.getId());
        Assert.assertNotNull(fetchedDeveloper);

        Assert.assertEquals(developer.getId(), fetchedDeveloper.getId());
        Assert.assertEquals(developer.getEmail(), fetchedDeveloper.getEmail());
        Assert.assertEquals(developer.getFirstName(), fetchedDeveloper.getFirstName());
        Assert.assertEquals(developer.getLastName(), fetchedDeveloper.getLastName());
        Assert.assertEquals(developer.getSkills().size(), fetchedDeveloper.getSkills().size());

        fetchedDeveloper.setEmail("abc@def.com");
        developerRepository.save(fetchedDeveloper);

        Developer fetchedUpdatedDeveloper = developerRepository.findOne(fetchedDeveloper.getId());
        Assert.assertEquals(fetchedUpdatedDeveloper.getEmail(), fetchedDeveloper.getEmail());

        Developer deleteDeveloper = new Developer();
        deleteDeveloper.setEmail("simple@mail.com");
        deleteDeveloper.setFirstName("name");
        deleteDeveloper.setLastName("lastName");
        developerRepository.save(deleteDeveloper);
        Assert.assertNotNull(deleteDeveloper.getId());

        long devCount = developerRepository.count();
        Assert.assertEquals(devCount, 2);

        developerRepository.delete(deleteDeveloper);
        devCount = developerRepository.count();
        Assert.assertEquals(devCount, 1);

        Iterable<Developer> developers = developerRepository.findAll();
        long count = 0;

        for(Developer d : developers){
            count++;
        }
        Assert.assertEquals(count, 1);
    }
}
