package com.example.exam.domain.dao;

import com.example.exam.domain.models.Book;
import com.example.exam.domain.models.SCard;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISCardRepository extends CrudRepository<SCard, Long> {
    List<SCard> findByDateInIsNull();
    List<SCard> findByDateInIsNotNull();

    @Query("SELECT s FROM SCard s WHERE s.book = :book AND s.dateIn > CURRENT_DATE")
    public List<SCard> findActiveLoansByBook(@Param("book") Book book);

}

