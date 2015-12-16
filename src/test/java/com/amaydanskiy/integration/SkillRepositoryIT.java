package com.amaydanskiy.integration;

import com.amaydanskiy.model.Skill;
import com.amaydanskiy.repository.SkillRepository;
import com.amaydanskiy.integration.configuration.RepositoryConfiguration;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {RepositoryConfiguration.class})
public class SkillRepositoryIT {

    private SkillRepository skillRepository;

    @Autowired
    public void setSkillRepository(SkillRepository skillRepository){
        this.skillRepository = skillRepository;
    }

    @Test
    public void testCRUDSkill(){
        Skill skill = new Skill();
        skill.setLabel("testLabel");
        skill.setDescription("testDescription");

        Assert.assertNull(skill.getId());
        skillRepository.save(skill);
        Assert.assertNotNull(skill.getId());

        Skill fetchedSkill = skillRepository.findOne(skill.getId());
        Assert.assertNotNull(fetchedSkill);

        Assert.assertEquals(fetchedSkill.getId(), skill.getId());
        Assert.assertEquals(fetchedSkill.getLabel(), skill.getLabel());
        Assert.assertEquals(fetchedSkill.getDescription(), skill.getDescription());

        fetchedSkill.setLabel("abcdefg");
        skillRepository.save(fetchedSkill);

        Skill fetchedUpdatedSkill = skillRepository.findOne(fetchedSkill.getId());
        Assert.assertEquals(fetchedUpdatedSkill.getLabel(), fetchedSkill.getLabel());

        Skill deleteSkill = new Skill();
        deleteSkill.setLabel("label");
        deleteSkill.setDescription("description");
        skillRepository.save(deleteSkill);
        Assert.assertNotNull(deleteSkill.getId());
        long skillCount = skillRepository.count();
        Assert.assertEquals(skillCount, 2);

        skillRepository.delete(deleteSkill);
        skillCount = skillRepository.count();
        Assert.assertEquals(skillCount, 1);

        long count = 0;
        Iterable <Skill> skills = skillRepository.findAll();
        for (Skill s : skills){
            count++;
        }
        Assert.assertEquals(count, 1);
    }
}
