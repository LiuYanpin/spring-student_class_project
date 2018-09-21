package com.thoughtworks.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ThirdBean {

    @Autowired
    SecondBean secondBean;

    public List<String> getBeanMessage() {
        return secondBean.getBeanMessage();
    }
}
