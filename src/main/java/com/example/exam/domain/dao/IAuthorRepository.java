package com.example.exam.domain.dao;

import com.example.exam.domain.models.Author;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAuthorRepository extends CrudRepository<Author, Long> {

    @Query("SELECT a FROM Author a WHERE :id_book IN (SELECT b.id FROM a.books b)")
    Iterable<Author> findAuthorsByBooks(long id_book);


}
