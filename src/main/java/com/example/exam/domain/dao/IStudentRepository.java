package com.example.exam.domain.dao;

import com.example.exam.domain.models.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IStudentRepository extends CrudRepository<Student, Long> {

    List<Student> findAll();
    Student findById(long id);
}


