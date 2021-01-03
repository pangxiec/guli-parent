package com.example.demo.edu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.annotation.ExcelProperty;
import com.example.demo.edu.entity.Subject;
import com.example.demo.edu.entity.subject.OneSubject;
import com.example.demo.edu.excel.SubjectData;
import com.example.demo.edu.listener.SubjectExcelListener;
import com.example.demo.edu.mapper.SubjectMapper;
import com.example.demo.edu.service.SubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.List;

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
    public void saveSublect(MultipartFile file,SubjectService SubjectService) {
        try{
            //文件输入流
            InputStream in = file.getInputStream();
            EasyExcel.read(in, SubjectData.class,new SubjectExcelListener(SubjectService)).sheet().doRead();
        }catch (Exception e){

        }
    }

    //课程分类列表(树型)
    @Override
    public List<OneSubject> getAllOneTwoSubject() {
        return null;
    }
}
