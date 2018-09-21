package com.thoughtworks.repository.impl;

import com.thoughtworks.domain.UniClass;
import com.thoughtworks.repository.ClassRepository;
import com.thoughtworks.repository.UniClassStorage;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public class ClassRepositoryImpl implements ClassRepository {
    public ClassRepositoryImpl() {
    }

    public ClassRepositoryImpl(List<Integer> list) {
        list.set(0, list.get(0) + 1);
    }

    @Override
    public Collection<UniClass> getClasses() {
        return UniClassStorage.getClasses();
    }
}
