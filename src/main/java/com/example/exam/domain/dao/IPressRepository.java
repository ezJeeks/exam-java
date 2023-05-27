package com.example.exam.domain.dao;

import com.example.exam.domain.models.Press;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPressRepository extends CrudRepository<Press, Long> {

}
