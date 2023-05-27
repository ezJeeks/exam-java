package com.example.exam.domain.dao;

import com.example.exam.domain.models.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoryRepository extends CrudRepository<Category, Long> {

}
