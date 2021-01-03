package com.example.demo.edu.service;

import com.example.demo.edu.entity.Subject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.edu.entity.subject.OneSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author xmy
 * @since 2021-01-02
 */
public interface SubjectService extends IService<Subject> {

    void saveSublect(MultipartFile file,SubjectService SubjectService);

    List<OneSubject> getAllOneTwoSubject();
}
