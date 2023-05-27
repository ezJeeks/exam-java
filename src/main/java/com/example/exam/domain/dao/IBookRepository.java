package com.example.exam.domain.dao;

import com.example.exam.domain.models.Book;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBookRepository extends CrudRepository<Book, Long>{

    @Query("SELECT s.book FROM SCard s WHERE s.dateIn > CURRENT_DATE")
    List<Book> findBooksOnHand();

    @Query("SELECT s.book FROM SCard s WHERE s.dateIn < CURRENT_DATE OR s.dateIn IS NULL")
    List<Book> findAvailableBooks();

    @Query("SELECT s.book FROM SCard s WHERE s.dateIn < CURRENT_DATE")
    List<Book> findOverdueBooks();

    @Query("SELECT b FROM Book b WHERE b.name LIKE %:title%")
    List<Book> findAvailableBooksByTitle(@Param("title") String title);

    @Query("SELECT b FROM Book b " +
            "LEFT JOIN b.authors a " +
            "LEFT JOIN b.press p " +
            "LEFT JOIN b.theme t " +
            "LEFT JOIN b.category c " +
            "WHERE (:authorId IS NULL OR a.Id = :authorId) " +
            "AND (:title IS NULL OR LOWER(b.name) LIKE LOWER(CONCAT('%', :title, '%'))) " +
            "AND (:year IS NULL OR b.year = :year) " +
            "AND (:pressId IS NULL OR p.id = :pressId) " +
            "AND (:themeId IS NULL OR t.id = :themeId) " +
            "AND (:categoryId IS NULL OR c.id = :categoryId)")
    List<Book> searchBooks(@Param("authorId") Long authorId,
                           @Param("title") String title,
                           @Param("year") Integer year,
                           @Param("pressId") Long pressId,
                           @Param("themeId") Long themeId,
                           @Param("categoryId") Long categoryId);
}

