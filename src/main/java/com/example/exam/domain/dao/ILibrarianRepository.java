package com.example.exam.domain.dao;

import com.example.exam.domain.models.Librarian;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ILibrarianRepository extends CrudRepository<Librarian, Long> {

    List<Librarian> findAll();

    Librarian findById(long id);
}
