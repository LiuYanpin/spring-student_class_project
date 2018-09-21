package com.thoughtworks.repository;

import com.thoughtworks.domain.Student;

import java.util.Collection;

public interface StudentRepository {
    Student putStudent(Student student);

    Collection<Student> queryStudentsByClassId(int classid);

    Collection<Student> queryStudentsByClassIdAndLowestAge(int classid, Integer age_gt);
}
