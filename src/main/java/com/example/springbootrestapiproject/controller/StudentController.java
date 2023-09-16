package com.example.springbootrestapiproject.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.springbootrestapiproject.bean.Student;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/students")
public class StudentController {

    // http://localhost:8080/student

    @GetMapping("/student")
    public ResponseEntity<Student> getStudent() {

        Student student = new Student(1, "Omkar", "Suvare");

        // return new ResponseEntity<Student>(student, HttpStatus.OK);
        // return ResponseEntity.ok(student);
        return ResponseEntity.ok().header("custom-header", "omkar").body(student);
    }

    // http://localhost:8080/students

    @GetMapping
    public ResponseEntity<List<Student>> getStudents() {

        List<Student> students = new ArrayList<>();

        students.add(new Student(1, "Omkar", "Suvare"));
        students.add(new Student(1, "Rushikesh", "Suvare"));
        students.add(new Student(1, "Swapnil", "Suvare"));

        return ResponseEntity.ok(students);
    }

    // SPRING BOOT REST API with Path Variable
    // {id} - URI template variable
    // http://localhost:8080/1

    @GetMapping("/{id}/{first-name}/{last-name}")
    public ResponseEntity<Student> studentPathVariable(@PathVariable("id") Integer studentId,
            @PathVariable("first-name") String firstName, @PathVariable("last-name") String lastName) {
        return ResponseEntity.ok(new Student(studentId, firstName, lastName));
    }

    // SPRING BOOT REST API with Reqest Param
    // http://localhost:8080/query?id=1&firstName=Omkar&lastName=Suvare

    @GetMapping("/query")
    public ResponseEntity<Student> studentRequestParam(@RequestParam("id") Integer studentId,
            @RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        return ResponseEntity.ok(new Student(studentId, firstName, lastName));
    }

    // SPRING BOOT REST API that handles HTTP POST Mapping - creating new resource

    @PostMapping("/create")
    // @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {

        log.info("Id: " + student.getId());
        log.info("FirstName: " + student.getFirstName());
        log.info("LastName: " + student.getLastName());

        return new ResponseEntity<Student>(student, HttpStatus.CREATED);
    }

    // SPRING BOOT REST API that handles HTTP PUT Mapping - updating existing
    // resource

    @PutMapping("/{id}/update")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student, @PathVariable("id") Integer studentId) {

        log.info("FirstName: " + student.getFirstName());
        log.info("LastName: " + student.getLastName());

        return ResponseEntity.ok(student);
    }

    // SPRING BOOT REST API that handles HTTP POST Mapping - deleting the existing
    // resource

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") Integer studentId) {
        log.info("Id: " + studentId);
        return ResponseEntity.ok("Student deleted Successfully");
    }
}
