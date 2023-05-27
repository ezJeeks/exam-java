package com.example.exam.domain.services;

import com.example.exam.domain.dao.IStudentRepository;
import com.example.exam.domain.models.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final IStudentRepository _student;

    public List<Student> getAllStudents() {
        return _student.findAll();
    }

    public Student getStudentById(Long id) {
        Optional<Student> optionalStudent = _student.findById(id);
        return optionalStudent.orElse(null);
    }
}
