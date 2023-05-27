package com.example.exam.controllers;

import com.example.exam.domain.dao.*;
import com.example.exam.domain.services.BookService;
import com.example.exam.domain.services.LibrarianService;
import com.example.exam.domain.services.StudentService;
import com.example.exam.domain.models.Book;
import com.example.exam.domain.models.Librarian;
import com.example.exam.domain.models.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BookController {
    private final IBookRepository _repo;
    private final ISCardRepository _scard;
    private final IStudentRepository _student;
    private final BookService bookService;
    private final StudentService studentService;
    private final LibrarianService librarianService;
    private final IAuthorRepository authorRepository;
    private final IPressRepository pressRepository;
    private final IThemeRepository themeRepository;
    private final ICategoryRepository categoryRepository;


    @RequestMapping("/books")
    public ModelAndView Books() {

        return new ModelAndView("book/books", "books", _repo.findAll());
    }

    @GetMapping("/books/on-hand")
    public String getBooksOnHand(Model model) {
        List<Book> books = _repo.findBooksOnHand();
        model.addAttribute("books", books);
        return "book/booksOnHand";
    }

    @GetMapping("/books/available")
    public String getAvailableBooks(Model model) {
        List<Book> books = _repo.findAvailableBooks();
        model.addAttribute("books", books);
        return "book/booksAvailable";
    }

    @GetMapping("/books/overdue")
    public String getOverdueBooks(Model model) {
        List<Book> books = _repo.findOverdueBooks();
        model.addAttribute("books", books);
        return "book/booksOverdue";
    }
    @GetMapping("/book-loan")
    public String getBookLoanPage(Model model) {
        List<Student> students = studentService.getAllStudents();
        List<Librarian> librarians = librarianService.getAllLibrarians();
        model.addAttribute("students", students);
        model.addAttribute("librarians", librarians);
        return "/book/book-loan";
    }

    @PostMapping("/book-loan")
    public String loanBook(@RequestParam String bookTitle, @RequestParam Long studentId, @RequestParam Long librarianId, Model model) {
        Book book = bookService.findAvailableBookByTitle(bookTitle);
        if (book == null) {
            model.addAttribute("errorMessage", "No available books found with this title");
            return "/book/book-loan";
        }
        Student student = studentService.getStudentById(studentId);
        Librarian librarian = librarianService.getLibrarianById(librarianId);
        LocalDate currentDate = LocalDate.now();
        LocalDate returnDate = currentDate.plusDays(10);
        bookService.loanBookToStudent(book, student, librarian, currentDate, returnDate);
        model.addAttribute("successMessage", "Book " + book.getName() + " has been loaned to " + student.getFirstName() + " " + student.getLastName());
        return "/book/book-loan";
    }
    @GetMapping("/books/search")
    public String searchBooks(Model model,
                              @RequestParam(name = "authorId", required = false) Long authorId,
                              @RequestParam(name = "title", required = false) String title,
                              @RequestParam(name = "year", required = false) Integer year,
                              @RequestParam(name = "pressId", required = false) Long pressId,
                              @RequestParam(name = "themeId", required = false) Long themeId,
                              @RequestParam(name = "categoryId", required = false) Long categoryId) {

        List<Book> books = _repo.searchBooks(authorId, title, year, pressId, themeId, categoryId);
        model.addAttribute("books", books);
        model.addAttribute("authors", authorRepository.findAll());
        model.addAttribute("presses", pressRepository.findAll());
        model.addAttribute("themes", themeRepository.findAll());
        model.addAttribute("categories", categoryRepository.findAll());
        return "book/booksSearch";
    }
}
