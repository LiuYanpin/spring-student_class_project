package com.thoughtworks.repository.impl;

import com.thoughtworks.domain.Student;
import com.thoughtworks.repository.StudentRepository;
import com.thoughtworks.repository.StudentStorage;

import java.util.Collection;

public class StudentRepositoryImpl implements StudentRepository {
    @Override
    public Student putStudent(Student student) {
        return StudentStorage.putStudent(student);
    }

    @Override
    public Collection<Student> queryStudentsByClassId(int classid) {
        return StudentStorage.getStudentsByClassId(classid);
    }

    @Override
    public Collection<Student> queryStudentsByClassIdAndLowestAge(int classid, Integer age_gt) {
        return StudentStorage.getStudentsByClassIdAndLowestAge(classid, age_gt);
    }

}
