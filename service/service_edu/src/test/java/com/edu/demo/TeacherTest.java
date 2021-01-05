package com.edu.demo;

import com.example.demo.edu.service.TeacherService;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * @author xmy
 * @data 2021/1/3 12:12
 */
public class TeacherTest {

    @Resource
    TeacherService teacherService;

    String id = "1189389726308478977";

    @Test
    public void getById(){
        System.out.println(teacherService.getById("1189389726308478977"));
    }
}
