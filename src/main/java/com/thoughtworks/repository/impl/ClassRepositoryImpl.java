package com.thoughtworks.repository.impl;

import com.thoughtworks.domain.UniClass;
import com.thoughtworks.repository.ClassRepository;
import com.thoughtworks.repository.UniClassStorage;

import java.util.Collection;

public class ClassRepositoryImpl implements ClassRepository {
    @Override
    public Collection<UniClass> getClasses() {
        return UniClassStorage.getClasses();
    }
}
