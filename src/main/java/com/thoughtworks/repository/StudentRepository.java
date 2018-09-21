package com.thoughtworks.repository;

import com.thoughtworks.domain.Student;

import java.util.Collection;

public interface StudentRepository {
    Student putStudent(Student student);

    Collection<Student> queryStudentsByClassId(int classid);

    Collection<Student> queryStudentsByClassIdAndLowestAge(int classId, Integer lowestAgehighestAge);

    Collection<Student> queryStudentsByClassIdAndHighestAge(int classId, Integer highestAge);
}
