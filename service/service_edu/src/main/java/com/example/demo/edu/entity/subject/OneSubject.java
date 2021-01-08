package com.example.demo.edu.entity.subject;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xmy
 * @data 2021/1/4 0:31
 */
@Data
public class OneSubject {

    private String id;

    private String title;

    //一个一级分类中有多个二级分类
    private List<TwoSubject> children = new ArrayList<>();
}
