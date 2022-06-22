/**
 * The StudentService class is a spring bean that is instantiated by the StudentController class, and it is responsible for
 * all of the business logic for the StudentController class
 */
// Telling the compiler that the class is in the package com.example.demo.student.
package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
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

    // The method is checking to see if a student with the given email exists, and if it does, it throws an exception. Otherwise, it
    // saves the student to the database.
    public void addNewStudent(Student student) {
        // Checking to see if a student with the given email exists.
        Optional<Student> studentByEmail = studentRepository
                .findStudentByEmail(student.getEmail());
        if (studentByEmail.isPresent()) {
            throw new IllegalStateException("email taken");
        }
        // Saving the student object to the database.
        studentRepository.save(student);
    }

    // method is checking to see if the student with the given id exists, and if it does, it deletes it from the database.
    public void deleteStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        // Checking to see if the student with the given id exists.
        if (!exists) {
            // This is throwing an exception if the student with the given id does not exist.
            throw new IllegalStateException("student with id " + studentId + " does not exist");
        }
        // Deleting the student with the given id from the database.
        studentRepository.deleteById(studentId);
    }
    // A Spring annotation that will make the method transactional.

    @Transactional
    public void updateStudent(Long studentId,
                              String name,
                              String email) {
        Student student = studentRepository.findById(studentId).
                // This is a lambda expression that is being passed into the orElseThrow method. The orElseThrow method is
                // a method that is part of the Optional class. The Optional class is a class that is used to represent a
                // value that may or may not be present. The orElseThrow method is used to return the value of the Optional
                // object if it is present, otherwise it throws an exception.
                orElseThrow(() -> new IllegalStateException(
                        "student with id " + studentId + " does not exist"));

        if (name != null &&
                name.length() > 0 && // The above is checking to see if the email is empty. Along with the email having a length greater than 0.
                // Checking to see if the name of the student object is equal to the name that is passed in.
                !Objects.equals(student.getName(), name)) {
            // Setting the name of the student object to the name that is passed in.
            student.setName(name);
        }

        if (email != null &&
                // The above is checking to see if the email is empty.
                // Along with the email having a length greater than 0.
                email.length() > 0 &&
                // Getting the email of the student object that is passed in.
                // Along with the email variable that is being passed into the updateStudent method.
                !Objects.equals(student.getEmail(), email)) {
            // Finding a student by the email that is passed in.
            Optional<Student> studentOptional = studentRepository
                    .findStudentByEmail(email);
            // Checking to see if the student with the given email exists, and if it does, it throws an exception.
            if (studentOptional.isPresent()) {
                throw new IllegalStateException("email taken");
            }
            // Setting the email of the student object to the email that is passed in.
            student.setEmail(email);
        }
    }
}
