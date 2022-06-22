package com.example.demo.student;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;

/**
 * It's a simple POJO with a few fields, a constructor, getters and setters, and a toString() method
 */
@Entity
// It's a JPA annotation. It's used to specify the table name.
@Table
public class Student {
    @Id
    // It's a sequence generator.
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    private Long id;
    private String name;
    private String email;
    private LocalDate dob;

    // It's a transient field. It's not persisted in the database.
    @Transient
    private Integer age;

    // It's a default constructor.
    public Student() {
    }

    // It's a constructor.
    public Student(Long id,
                   String name,
                   String email,
                   LocalDate dob) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.dob = dob;

    }

    // It's a constructor.
    public Student(String name,
                   String email,
                   LocalDate dob) {
        this.name = name;
        this.email = email;
        this.dob = dob;

    }

    // It's a getter and setter for the id, name, email, dob..etc fields.
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Integer getAge() {
        return Period.between(this.dob, LocalDate.now()).getYears();
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * The toString() method returns a string representation of the object
     *
     * @return The toString() method is being overridden to return the values of the Student object.
     */
    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", dob=" + dob +
                ", age=" + age +
                '}';
    }
}
