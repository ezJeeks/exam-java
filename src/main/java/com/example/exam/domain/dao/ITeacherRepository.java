package com.example.exam.domain.dao;

import com.example.exam.domain.models.Teacher;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITeacherRepository extends CrudRepository<Teacher, Long> {

    List<Teacher> findAll();
    Teacher findById(long id);
}



