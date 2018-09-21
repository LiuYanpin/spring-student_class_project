package com.thoughtworks.bean;

import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public class FirstBean {

    public String firstMethod() {
        return LocalDateTime.now().toString();
    }
    public String secondMethod() {
        return LocalDateTime.now().toString();
    }
    public String thirdMethod() {
        return LocalDateTime.now().toString();
    }
}
