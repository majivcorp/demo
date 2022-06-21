package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
//All of the resources for our API Layer, data is passed and processed, from the and to the StudentService layer
@RestController
@RequestMapping(path ="api/v1/student")

public class StudentController {

    private final StudentService studentService; // Student service is to be injected into the constructor below, once instantiated from StudentService

@Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getStudents(){
        return studentService.getStudents();
    }
}

