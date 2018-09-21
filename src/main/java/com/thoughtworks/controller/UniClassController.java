package com.thoughtworks.controller;

import com.thoughtworks.domain.Student;
import com.thoughtworks.repository.ClassRepository;
import com.thoughtworks.repository.StudentRepository;
import com.thoughtworks.repository.impl.ClassRepositoryImpl;
import com.thoughtworks.repository.impl.StudentRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UniClassController {
    @Autowired
    ClassRepository classRepository;

    @Autowired
    StudentRepository studentRepository;

    @GetMapping("/api/classes")
    public ResponseEntity getClasses() {
        try {
            return new ResponseEntity(classRepository.getClasses(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/api/classes/{classid}/students")
    public ResponseEntity postStudent(@PathVariable int classid, @RequestBody Student student) {
        try {
            return new ResponseEntity(studentRepository.putStudent(student), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/api/classes/{classid}/students")
    public ResponseEntity getStudentsByClassId(@PathVariable int classid,
                                                 @RequestParam(required = false, value = "age_gt") Integer lowestAge,
                                                 @RequestParam(required = false, value = "age_lt") Integer highestAge) {
        try {
            if (lowestAge != null) {
                return new ResponseEntity(studentRepository.queryStudentsByClassIdAndLowestAge(classid, lowestAge), HttpStatus.OK);
            } else if (highestAge != null) {
                return new ResponseEntity(studentRepository.queryStudentsByClassIdAndHighestAge(classid, highestAge), HttpStatus.OK);
            }
            return new ResponseEntity(studentRepository.queryStudentsByClassId(classid), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
