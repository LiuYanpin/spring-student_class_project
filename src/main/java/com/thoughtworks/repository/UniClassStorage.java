package com.thoughtworks.repository;

import com.thoughtworks.domain.Student;
import com.thoughtworks.domain.UniClass;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class UniClassStorage {
    private static final Map<Integer, UniClass> UNI_CLASS = new HashMap<>();

    public static void putClass(UniClass uniClass) {
        UNI_CLASS.put(uniClass.getId(), uniClass);
    }

    public static Collection<UniClass> getClasses() {
        return UNI_CLASS.values();
    }

    public static void clear() {
        UNI_CLASS.clear();
    }
}
