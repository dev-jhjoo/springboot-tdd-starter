package dev.jh.joo.jmeter.controller;

import dev.jh.joo.jmeter.service.ClassService;
import dev.jh.joo.testcontainers.Classes;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ClassController {

    final ClassService service;

    @GetMapping("/class/{id}")
    public Classes getClasses(@PathVariable Long id){
        return service.getClasses(id);
    }

    @PostMapping("/class")
    public Classes createClass(@RequestBody Classes classes){
        return service.saveClasses(classes);
    }

}
