package com.example.demo.edu.service.impl;

import com.example.demo.edu.entity.Course;
import com.example.demo.edu.entity.CourseDescription;
import com.example.demo.edu.entity.vo.CourseInfoVo;
import com.example.demo.edu.mapper.CourseDescriptionMapper;
import com.example.demo.edu.mapper.CourseMapper;
import com.example.demo.edu.service.CourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.servicebase.exceptionhandler.GuliException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author xmy
 * @since 2021-01-07
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

    @Resource
    CourseDescriptionMapper descriptionMapper;

    @Override
    public String addCourseInfo(CourseInfoVo courseInfoVo) {
        //向课程表中添加课程信息
        Course course = new Course();
        BeanUtils.copyProperties(courseInfoVo,course);
        int insert = baseMapper.insert(course);
        if (insert == 0){
            throw new GuliException(20001,"添加课程信息失败");
        }

        //添加成功过后获取Id值
        String cid = course.getId();

        //向课程描述表中添加课程描述信息
        CourseDescription courseDescription = new CourseDescription();
        courseDescription.setDescription(courseInfoVo.getDescription());
        courseDescription.setId(cid);
        descriptionMapper.insert(courseDescription);

        return cid;
    }
}
