/**
 * The StudentController class is a REST controller that has a method that returns a list of students and another method
 * that registers a new student
 */
package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//All of the resources for our API Layer, data is passed and processed, from the and to the StudentService layer
/**
 * The StudentController class is a REST controller that has a method that returns a list of students and another method
 * that registers a new student
 */
@RestController
@RequestMapping(path ="api/v1/student")

public class StudentController {

    // This is a constructor that is being used to inject the StudentService into the StudentController.
    private final StudentService studentService; // Student service is to be injected into the constructor below, once instantiated from StudentService

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getStudents(){
        return studentService.getStudents();
    }
     @PostMapping
    // Taking the RequestBody "Student" and mapping it into "student"
    public void registerNewStudent(@RequestBody Student student) {// the RequestBody "Student" is taken and mapped into "student"
        studentService.addNewStudent(student);
    }
}

