package com.amaydanskiy.integration;

import com.amaydanskiy.model.Developer;
import com.amaydanskiy.repository.DeveloperRepository;
import com.amaydanskiy.model.Skill;
import com.amaydanskiy.repository.SkillRepository;
import com.amaydanskiy.integration.configuration.RepositoryConfiguration;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {RepositoryConfiguration.class})
public class DeveloperRepositoryITest {

    @Autowired
    private DeveloperRepository developerRepository;
    @Autowired
    private SkillRepository skillRepository;

    private final Skill skill1 = new Skill();
    private final Skill skill2 = new Skill();
    private final Developer developer = new Developer();

    @Before
    public void setUp(){

        this.developerRepository.deleteAll();
        this.skillRepository.deleteAll();

        skill1.setLabel("test1label");
        skill1.setDescription("tst1Description");
        this.skillRepository.save(skill1);

        skill2.setLabel("test2label");
        skill2.setDescription("tst2Description");
        this.skillRepository.save(skill2);

        developer.setEmail("test@mail.com");
        developer.setFirstName("testFirstName");
        developer.setLastName("testLastName");
        developer.setSkills(new ArrayList<Skill>(){{
            add(skill1);
            add(skill2);
        }});
    }

    @After
    public void tearDown(){
        this.developerRepository.deleteAll();
        this.skillRepository.deleteAll();
    }

    @Test
    public void testSaveDeveloper() {

        Assert.assertNull(developer.getId());
        developerRepository.save(developer);
        Assert.assertNotNull(developer.getId());

        final Developer fetchedDeveloper = developerRepository.findOne(developer.getId());
        Assert.assertNotNull(fetchedDeveloper);

        Assert.assertEquals(developer.getId(), fetchedDeveloper.getId());
        Assert.assertEquals(developer.getEmail(), fetchedDeveloper.getEmail());
        Assert.assertEquals(developer.getFirstName(), fetchedDeveloper.getFirstName());
        Assert.assertEquals(developer.getLastName(), fetchedDeveloper.getLastName());
//        Assert.assertEquals(developer.getSkills().size(), fetchedDeveloper.getSkills().size());
    }

    @Test
    public void testUpdateDeveloper() {
        developerRepository.save(developer);
        final Developer fetchedDeveloper = developerRepository.findOne(developer.getId());
        fetchedDeveloper.setEmail("abc@def.com");
        developerRepository.save(fetchedDeveloper);

        final Developer fetchedUpdatedDeveloper = developerRepository.findOne(fetchedDeveloper.getId());
        Assert.assertEquals(fetchedUpdatedDeveloper.getEmail(), fetchedDeveloper.getEmail());
    }

    @Test
    public void testDeleteDeveloper() {
        final Developer deleteDeveloper = new Developer();
        deleteDeveloper.setEmail("simple@mail.com");
        deleteDeveloper.setFirstName("name");
        deleteDeveloper.setLastName("lastName");
        developerRepository.save(deleteDeveloper);
        Assert.assertNotNull(deleteDeveloper.getId());

        long devCount = developerRepository.count();
        Assert.assertEquals(1, devCount);

        developerRepository.delete(deleteDeveloper);
        devCount = developerRepository.count();
        Assert.assertEquals(0, devCount);
    }

    @Test
    public void testDataPersistence(){
        developerRepository.save(developer);
        final Iterable<Developer> developers = developerRepository.findAll();
        long count = 0;

        for(Developer d : developers){
            count++;
        }
        Assert.assertEquals(1, count);
    }
}
