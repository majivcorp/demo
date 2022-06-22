/**
 * The StudentService class is a spring bean that is instantiated by the StudentController class, and it is responsible for
 * all of the business logic for the StudentController class
 */
package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//All of the resources for our Service Layer, responsible for our business logic, data is passed and processed, from the and to the StudentController(Api layer) layer

// A spring annotation that is used to inject a dependency into a class.
@Service // The @service component is  telling us that the class is a spring bean to be instantiated to StudentController
public class StudentService {

    // This is a constructor for the StudentService class. It is taking in a StudentRepository object and assigning it to
    // the studentRepository variable.
    private final StudentRepository studentRepository;

    @Autowired // A spring annotation that is used to inject a dependency into a class.
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    /**
     * Get all students from the database and return them as a list.
     *
     * @return A list of all students in the database.
     */
    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    /**
     * "If a student with the given email exists, throw an exception, otherwise save the student."
     *
     * The problem with this function is that it's not very readable. It's hard to tell what the function is doing at a
     * glance
     *
     * @param student The student object that is being passed in from the controller.
     */
    public void addNewStudent(Student student) {
        Optional<Student> studentByEmail = studentRepository
                .findStudentByEmail(student.getEmail());
        if (studentByEmail.isPresent()) {
            throw new IllegalStateException("email taken");
        }
        studentRepository.save(student);
    }
}
