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
    @ManyToMany(fetch = FetchType.LAZY)
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

        return getSkills().stream().anyMatch(s -> s.equals(skill));

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Developer developer = (Developer) o;

        if (!firstName.equals(developer.firstName)) return false;
        if (lastName != null ? !lastName.equals(developer.lastName) : developer.lastName != null) return false;
        if (email != null ? !email.equals(developer.email) : developer.email != null) return false;
        return skills != null ? skills.equals(developer.skills) : developer.skills == null;

    }

    @Override
    public int hashCode() {
        int result = firstName.hashCode();
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (skills != null ? skills.hashCode() : 0);
        return result;
    }
}
