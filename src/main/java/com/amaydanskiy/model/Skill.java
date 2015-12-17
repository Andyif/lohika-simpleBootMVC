package com.amaydanskiy.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Skill {

    @Id
    @GeneratedValue
    private Long    id;
    @Column(nullable = false)
    private String  label;
    private String  description;

    public Skill() {
    }

    public Skill(Long id, String label, String description) {
        this.id             = id;
        this.label          = label;
        this.description    = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Skill skill = (Skill) o;

        if (!label.equals(skill.label)) return false;
        return !(description != null ? !description.equals(skill.description) : skill.description != null);

    }

    @Override
    public int hashCode() {
        int result = label.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
