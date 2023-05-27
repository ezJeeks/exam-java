package com.example.exam.domain.services;

import com.example.exam.domain.dao.ILibrarianRepository;
import com.example.exam.domain.models.Librarian;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LibrarianService {
    private final ILibrarianRepository _librarian;

    public List<Librarian> getAllLibrarians() {
        return _librarian.findAll();
    }

    public Librarian getLibrarianById(long id) {
        return _librarian.findById(id);
    }

    public Librarian saveLibrarian(Librarian librarian) {
        return _librarian.save(librarian);
    }
}
