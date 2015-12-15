package com.amaydanskiy.integration;

import com.amaydanskiy.Developer;
import com.amaydanskiy.DeveloperRepository;
import com.amaydanskiy.integration.configuration.RepositoryConfiguration;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {RepositoryConfiguration.class})
public class DeveloperRepositoryIT {

    private DeveloperRepository developerRepository;

    @Autowired
    public void setDeveloperRepository(DeveloperRepository developerRepository){
        this.developerRepository = developerRepository;
    }

    @Test
    public void testCRUDDeveloper(){
        Developer developer = new Developer();
        developer.setEmail("test@mail.com");
        developer.setFirstName("testFirstName");
        developer.setLastName("testLastName");

        Assert.assertNull(developer.getId());
        developerRepository.save(developer);
        Assert.assertNotNull(developer.getId());

        Developer fetchedDeveloper = developerRepository.findOne(developer.getId());
        Assert.assertNotNull(fetchedDeveloper);

        Assert.assertEquals(developer.getId(), fetchedDeveloper.getId());
        Assert.assertEquals(developer.getEmail(), fetchedDeveloper.getEmail());
        Assert.assertEquals(developer.getFirstName(), fetchedDeveloper.getFirstName());
        Assert.assertEquals(developer.getLastName(), fetchedDeveloper.getLastName());

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
