package com.example.exam.domain.dao;

import com.example.exam.domain.models.Group;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IGroupRepository extends CrudRepository<Group, Long> {

}
