package com.example.exam.controllers;

import com.example.exam.domain.services.BookService;
import com.example.exam.domain.services.LibrarianService;
import com.example.exam.domain.services.StudentService;
import com.example.exam.domain.services.TeacherService;
import com.example.exam.domain.models.Book;
import com.example.exam.domain.models.Librarian;
import com.example.exam.domain.models.Student;
import com.example.exam.domain.models.Teacher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class LibrarianController {

    private final BookService bookService;
    private final TeacherService teacherService;
    private final StudentService studentService;
    private final LibrarianService librarianService;

    @GetMapping("/tcard/add")
    public String getCart_T(Model model) {
        List<Teacher> teachers = teacherService.getAllTeachers();
        List<Librarian> librarians = librarianService.getAllLibrarians();
        model.addAttribute("teachers", teachers);
        model.addAttribute("librarians", librarians);
        return "/librarian/lendingToTeachers";
    }

    @PostMapping("/tcard/add")
    public String postCart_T(@RequestParam String bookTitle,
                           @RequestParam Long teacherId,
                           @RequestParam Long librarianId,
                           Model model) {
        Book book = bookService.findAvailableBookByTitle(bookTitle);
        if (book == null) {
            model.addAttribute("errorMessage", "No available books found with this title");
            return "/librarian/lendingToTeachers";
        }
        Teacher teacher = teacherService.getTeacherById(teacherId);
        Librarian librarian = librarianService.getLibrarianById(librarianId);
        LocalDate currentDate = LocalDate.now();
        LocalDate returnDate = currentDate.plusDays(10);
        bookService.loanBookToTeacher(book, teacher, librarian, currentDate, returnDate);
        model.addAttribute("successMessage", "Book " + book.getName() + " has been loaned to " + teacher.getFirstName() + " " + teacher.getLastName());
        return "/librarian/lendingToTeachers";
    }

    @GetMapping("/scard/add")
    public String getBookLoanPage(Model model) {
        List<Student> students = studentService.getAllStudents();
        List<Librarian> librarians = librarianService.getAllLibrarians();
        model.addAttribute("students", students);
        model.addAttribute("librarians", librarians);
        return "/librarian/lendingToStudents";
    }

    @PostMapping("/scard/add")
    public String loanBook(@RequestParam String bookTitle, @RequestParam Long studentId, @RequestParam Long librarianId, Model model) {
        Book book = bookService.findAvailableBookByTitle(bookTitle);
        if (book == null) {
            model.addAttribute("errorMessage", "No available books found with this title");
            return "/librarian/lendingToStudents";
        }
        Student student = studentService.getStudentById(studentId);
        Librarian librarian = librarianService.getLibrarianById(librarianId);
        LocalDate currentDate = LocalDate.now();
        LocalDate returnDate = currentDate.plusDays(10);
        bookService.loanBookToStudent(book, student, librarian, currentDate, returnDate);
        model.addAttribute("successMessage", "Book " + book.getName() + " has been loaned to " + student.getFirstName() + " " + student.getLastName());
        return "/librarian/lendingToStudents";
    }
}
