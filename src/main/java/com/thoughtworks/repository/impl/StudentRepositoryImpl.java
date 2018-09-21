package com.thoughtworks.repository.impl;

import com.thoughtworks.domain.Student;
import com.thoughtworks.repository.StudentRepository;
import com.thoughtworks.repository.StudentStorage;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
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
    public Collection<Student> queryStudentsByClassIdAndLowestAge(int classId, Integer lowestAge) {
        return StudentStorage.getStudentsByClassIdAndLowestAge(classId, lowestAge);
    }

    @Override
    public Collection<Student> queryStudentsByClassIdAndHighestAge(int classId, Integer highestAge) {
        return StudentStorage.getStudentsByClassIdAndHighestAge(classId, highestAge);
    }
}
