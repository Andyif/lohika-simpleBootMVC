package com.amaydanskiy.repository;

import com.amaydanskiy.model.Skill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SkillRepository extends PagingAndSortingRepository<Skill, Long> {
    Page<Skill> findAllOrderByLabel(Pageable pageable);
}
