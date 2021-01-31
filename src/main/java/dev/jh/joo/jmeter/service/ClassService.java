package dev.jh.joo.jmeter.service;

import dev.jh.joo.testcontainers.Classes;
import dev.jh.joo.testcontainers.ClassesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClassService {

    final ClassesRepository repository;

    public Classes saveClasses(Classes classes) {
        return repository.save(classes);
    }

    public Classes getClasses(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(id + " is invalid ID !!"));
    }
}
