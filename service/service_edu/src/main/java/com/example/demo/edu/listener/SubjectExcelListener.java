package com.example.demo.edu.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.edu.entity.Subject;
import com.example.demo.edu.excel.SubjectData;
import com.example.demo.edu.service.SubjectService;
import com.example.servicebase.exceptionhandler.GlobalExceptionHandler;
import com.example.servicebase.exceptionhandler.GuliException;

/**
 * @author xmy
 * @data 2021/1/2 20:32
 */
public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {

    private SubjectService subjectService;

    public SubjectExcelListener() {

    }
    public SubjectExcelListener(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
        if (subjectData == null){
            throw new GuliException(20001,"文件数据为空");
        }
        //一行一行的读取 每行有两个值 第一个值是一级分类 第二个值是二级分类
        //判断一级分类是否重复
        Subject subjectOne = this.existOneSubject(subjectService, subjectData.getOneSubjectName());
        //没有一级分类进行添加
        if (subjectOne == null){
            subjectOne = new Subject();
            subjectOne.setTitle(subjectData.getOneSubjectName());
            subjectOne.setParentId("0");
            subjectService.save(subjectOne);
        }

        String pid = subjectOne.getId();
        //判断二级分类是否重复
        Subject subjectTwo = this.existTwoSubject(subjectService, subjectData.getTwoSubjectName(),pid);
        //没有二级分类进行添加
        if (subjectTwo == null){
            subjectTwo = new Subject();
            subjectTwo.setTitle(subjectData.getTwoSubjectName());
            subjectTwo.setParentId(pid);
            subjectService.save(subjectTwo);
        }

    }

    //判断一级分类不能重复添加
    private Subject existOneSubject(SubjectService subjectService,String name){
        QueryWrapper<Subject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title",name);
        queryWrapper.eq("parent_id",0);
        Subject oneSubject = subjectService.getOne(queryWrapper);
        return oneSubject;
    }

    //判断二级分类不能重复添加
    private Subject existTwoSubject(SubjectService subjectService,String name,String pid){
        QueryWrapper<Subject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title",name);
        queryWrapper.eq("parent_id",pid);
        Subject TwoSubject = subjectService.getOne(queryWrapper);
        return TwoSubject;
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
