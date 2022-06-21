package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
//All of the resources for our Service Layer, responsible for our business logic, data is passed and processed, from the and to the StudentController(Api layer) layer
@Service // The @service component is  telling us that the class is a spring bean to be instantiated to StudentController
public class StudentService {

    private final StudentRepository studentRepository;
    @Autowired //
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }
}
