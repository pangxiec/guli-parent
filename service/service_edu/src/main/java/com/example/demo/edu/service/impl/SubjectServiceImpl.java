package com.example.demo.edu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.edu.entity.Subject;
import com.example.demo.edu.entity.subject.OneSubject;
import com.example.demo.edu.entity.subject.TwoSubject;
import com.example.demo.edu.excel.SubjectData;
import com.example.demo.edu.listener.SubjectExcelListener;
import com.example.demo.edu.mapper.SubjectMapper;
import com.example.demo.edu.service.SubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.ArrayList;
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
        //先查出一级和二级分类的数据数据
        //一级分类的数据
        QueryWrapper<Subject> one = new QueryWrapper<>();
        one.eq("parent_id",0);
        List<Subject> oneList = baseMapper.selectList(one);

        //二级分类的数据
        QueryWrapper<Subject> two = new QueryWrapper<>();
        two.ne("parent_id",0);
        List<Subject> twoList = baseMapper.selectList(two);

        //将查询出来的数据进行封装
        //存放最终的结果
        List<OneSubject> finalSubjectList = new ArrayList<>();

        for (int i = 0; i < oneList.size(); i++) {
            //将查询到的数据取出来
            Subject subject = oneList.get(i);
            OneSubject oneSubject = new OneSubject();
            BeanUtils.copyProperties(subject,oneSubject);

            finalSubjectList.add(oneSubject);

            //处理二级分类
            //二级分类是在一级分类的里面
            List<TwoSubject> twoFinalSubjectList = new ArrayList<>();
            for (int j = 0; j < twoList.size(); j++) {
                Subject subject1 = twoList.get(j);
                //判断二级分类的id和一级分类的id是否一样
                if (subject1.getParentId().equals(oneSubject.getId())){
                    TwoSubject twoSubject = new TwoSubject();
                    BeanUtils.copyProperties(subject1,twoSubject);
                    twoFinalSubjectList.add(twoSubject);
                }
            }
            //把所有二级分类放到一级分类下面去
            oneSubject.setChild(twoFinalSubjectList);
        }
        return finalSubjectList;
    }
}
