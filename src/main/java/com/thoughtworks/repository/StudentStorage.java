package com.thoughtworks.repository;

import com.thoughtworks.domain.Student;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class StudentStorage {
    private final static Map<Integer, Student> STUDENT = new HashMap<>();

    public static Student putStudent(Student student) {
        STUDENT.put(student.getId(), student);
        return student;
    }

    public static Collection<Student> getStudents() {
        return STUDENT.values();
    }

    public static void putStudents(Student...students) {
        Arrays.stream(students).forEach((student -> STUDENT.put(student.getId(), student)));
    }

    public static Collection<Student> getStudentsByClassId(int classId) {
        return STUDENT.values().stream().filter(student -> student.getClassId() == classId).collect(Collectors.toList());
    }

    public static Collection<Student> getStudentsByClassIdAndLowestAge(int classId, Integer lowestAge) {
        return STUDENT.values().stream()
                .filter(student -> student.getClassId() == classId && student.getAge() > lowestAge)
                .collect(Collectors.toList());
    }

    public static Collection<Student> getStudentsByClassIdAndHighestAge(int classId, Integer highestAge) {
        return STUDENT.values().stream()
                .filter(student -> student.getClassId() == classId && student.getAge() < highestAge)
                .collect(Collectors.toList());
    }

    public static void clear() {
        STUDENT.clear();
    }
}
