package com.example.exam.domain.dao;

import com.example.exam.domain.models.*;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFacultyRepository extends CrudRepository<Faculty, Long> {

}

