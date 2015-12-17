package com.amaydanskiy.model;

import com.amaydanskiy.model.Skill;

import javax.persistence.*;
import java.util.List;

@Entity
public class Developer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long        id;
    @Column(nullable = false)
    private String      firstName;
    private String      lastName;
    private String      email;
    @ManyToMany
    private List<Skill> skills;

    public Developer() {
    }

    public Developer(Long id, String firstName, String lastName, String email, List<Skill> skills) {
        this.id = id;
        this.firstName  = firstName;
        this.lastName   = lastName;
        this.email      = email;
        this.skills     = skills;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    public boolean hasSkill(final Skill skill){
        for (Skill containedSkill : getSkills()){
            if (containedSkill.getId() == skill.getId()){
                return true;
            }
        }
        return false;
    }


}
