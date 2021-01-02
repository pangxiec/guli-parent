package com.example.demo.edu.service.impl;

import com.example.demo.edu.entity.Subject;
import com.example.demo.edu.mapper.SubjectMapper;
import com.example.demo.edu.service.SubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author xmy
 * @since 2021-01-02
 */
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectService {

    @Resource
    private SubjectMapper subjectMapper;

    @Override
    public void saveSublect(MultipartFile file) {

    }
}
