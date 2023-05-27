package com.example.exam.domain.services;

import com.example.exam.domain.dao.ITeacherRepository;
import com.example.exam.domain.models.Teacher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final ITeacherRepository _teacher;

    public List<Teacher> getAllTeachers() {
        return _teacher.findAll();
    }

    public Teacher getTeacherById(Long id) {
        Optional<Teacher> optionalTeacher = _teacher.findById(id);
        return optionalTeacher.orElse(null);
    }
}
