package com.thoughtworks.controller;

import com.thoughtworks.domain.Student;
import com.thoughtworks.repository.ClassRepository;
import com.thoughtworks.repository.StudentRepository;
import com.thoughtworks.repository.impl.ClassRepositoryImpl;
import com.thoughtworks.repository.impl.StudentRepositoryImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UniClassController {
    ClassRepository classRepository = new ClassRepositoryImpl();
    StudentRepository studentRepository = new StudentRepositoryImpl();

    @GetMapping("/api/classes")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity getClasses() {
        return new ResponseEntity(classRepository.getClasses(), HttpStatus.OK);
    }

    @PostMapping("/api/classes/{classid}/students")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity postStudent(@PathVariable int classid, @RequestBody Student student) {
        return new ResponseEntity(studentRepository.putStudent(student), HttpStatus.CREATED);
    }

    @GetMapping("/api/classes/{classid}/students")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity getStudentsByClassId(@PathVariable int classid,
                                                 @RequestParam(required = false) Integer age_gt,
                                                 @RequestParam(required = false) Integer age_lt) {
        if (age_gt != null) {
            return new ResponseEntity(studentRepository.queryStudentsByClassIdAndLowestAge(classid, age_gt), HttpStatus.OK);
        }
        return new ResponseEntity(studentRepository.queryStudentsByClassId(classid), HttpStatus.OK);

    }
}
