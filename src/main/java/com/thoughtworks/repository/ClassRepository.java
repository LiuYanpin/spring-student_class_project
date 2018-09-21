package com.thoughtworks.repository;

import com.thoughtworks.domain.UniClass;

import java.util.Collection;

public interface ClassRepository {
    Collection<UniClass> getClasses();
}
