package com.goebuy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.goebuy.entity.QuestionEntity;

@EnableJpaRepositories
@Repository
public interface  QuestionRepository extends JpaRepository<QuestionEntity, Integer>{

}
