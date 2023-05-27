package com.example.exam.domain.dao;

import com.example.exam.domain.models.Department;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IDepartmentRepository extends CrudRepository<Department, Long> {

    List<Department> findAll();

}


