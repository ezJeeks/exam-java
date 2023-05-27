package com.example.exam.domain.services;

import com.example.exam.domain.dao.IBookRepository;
import com.example.exam.domain.dao.ISCardRepository;
import com.example.exam.domain.dao.ITCardRepository;
import com.example.exam.domain.models.*;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final ISCardRepository _sCard;
    private final ITCardRepository _tCard;
    private final IBookRepository _books;



    public List<Book> findBooksOnHand() {
        List<Book> booksOnHand = new ArrayList<>();

        List<SCard> sCards = _sCard.findByDateInIsNull();

        for (SCard sCard : sCards) {
            booksOnHand.add(sCard.getBook());
        }

        return booksOnHand;
    }

    public List<Book> findBooksAvailable() {
        List<Book> booksAvailable = new ArrayList<>();

        List<SCard> sCards = _sCard.findByDateInIsNotNull();

        for (SCard sCard : sCards) {
            booksAvailable.add(sCard.getBook());
        }

        return booksAvailable;
    }

    public List<Book> findOverdueBooks() {
        List<Book> booksOverdue = new ArrayList<>();

        List<SCard> sCards = _sCard.findByDateInIsNotNull();

        for (SCard sCard : sCards) {
            booksOverdue.add(sCard.getBook());
        }

        return booksOverdue;
    }

    public Book findAvailableBookByTitle(String title) {
        List<Book> books = _books.findAvailableBooksByTitle(title);
        return books.isEmpty() ? null : books.get(0);
    }

    public boolean loanBookToStudent(Book book, Student student, Librarian librarian, LocalDate currentDate, LocalDate returnDate) {
        List<SCard> activeLoans = _sCard.findActiveLoansByBook(book);
        if (!activeLoans.isEmpty()) {
            return false;
        }

        SCard scard = new SCard();
        scard.setBook(book);
        scard.setStudent(student);
        scard.setDateOut(Date.valueOf(currentDate));
        scard.setDateIn(Date.valueOf(returnDate));
        scard.setLibrarian(librarian);
        _sCard.save(scard);

        return true;
    }
    public boolean loanBookToTeacher(Book book, Teacher teacher, Librarian librarian, LocalDate currentDate, LocalDate returnDate) {
        List<TCard> activeLoans = _tCard.findActiveLoansByBook(book);
        if (!activeLoans.isEmpty()) {
            return false;
        }

        TCard tcard = new TCard();
        tcard.setBook(book);
        tcard.setTeacher(teacher);
        tcard.setDateOut(Date.valueOf(currentDate));
        tcard.setDateIn(Date.valueOf(returnDate));
        tcard.setLibrarian(librarian);
        _tCard.save(tcard);

        return true;
    }

}

