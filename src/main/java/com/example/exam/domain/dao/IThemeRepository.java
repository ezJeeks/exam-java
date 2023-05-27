package com.example.exam.domain.dao;

import com.example.exam.domain.models.Theme;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IThemeRepository extends CrudRepository<Theme, Long> {

}
