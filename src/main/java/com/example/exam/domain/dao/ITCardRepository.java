package com.example.exam.domain.dao;

import com.example.exam.domain.models.Book;
import com.example.exam.domain.models.TCard;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITCardRepository extends CrudRepository<TCard, Long> {


    @Query("SELECT t FROM TCard t WHERE t.book = :book AND t.dateIn > CURRENT_DATE")
    public List<TCard> findActiveLoansByBook(@Param("book") Book book);
}
