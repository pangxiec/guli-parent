package com.example.demo.edu.controller;


import com.example.common.R;
import com.example.demo.edu.service.SubjectService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author xmy
 * @since 2021-01-02
 */
@RestController
@RequestMapping("/eduservice/subject")
@CrossOrigin
public class SubjectController {

    @Resource
    private SubjectService subjectService;

    /**
     * 添加课程分类
     * 获取上传过来的文件 把文件内容读取出来
     *
     * @param file
     * @return
     */
    @PostMapping("/addSubject")
    public R addSubject(MultipartFile file){
        //上传过来的excel文件
        subjectService.saveSublect(file);
        return R.ok();
    }
}

