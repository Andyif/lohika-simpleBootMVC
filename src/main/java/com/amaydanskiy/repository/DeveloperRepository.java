package com.amaydanskiy.repository;

import com.amaydanskiy.model.Developer;
import com.amaydanskiy.model.Skill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface DeveloperRepository extends PagingAndSortingRepository<Developer, Long> {
    Page<Developer> findByIdOrderByIdAscAllIgnoreCase(Pageable pageable);
    List<Developer> findBySkillsLabelOrderByIdAscAllIgnoreCase(String label);
}
