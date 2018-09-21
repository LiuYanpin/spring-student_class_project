package com.thoughtworks.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class SecondBean {

    @Autowired
    private FirstBean firstBean;

    public List<String> getBeanMessage() {
        List<String> list = new ArrayList<>();
        list.add(firstBean.firstMethod());
        list.add(firstBean.secondMethod());
        list.add(firstBean.thirdMethod());
        return list;
    }
}
