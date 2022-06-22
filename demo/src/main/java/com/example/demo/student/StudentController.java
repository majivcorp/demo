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

    /**
     * Delete a student by id
     *
     * @param studentId This is the path variable that will be used to identify the student to be deleted.
     */

    @DeleteMapping(path = "{studentId}")
    // This is a method that is used to delete a student by id.
    public void deleteStudent(
            @PathVariable("studentId") Long studentId) {
        // Calling the deleteStudent method in the StudentService class.
        studentService.deleteStudent(studentId);
    }

    @PutMapping(path = "{studentId}")
    public void updateStudent(
            @PathVariable("studenntId") Long studentId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email) {
        studentService.updateStudent(studentId, name, email);
    }
}

