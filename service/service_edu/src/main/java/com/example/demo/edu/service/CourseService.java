package com.example.demo.edu.service;

import com.example.demo.edu.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.edu.entity.vo.CourseInfoVo;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author xmy
 * @since 2021-01-07
 */
public interface CourseService extends IService<Course> {

    String addCourseInfo(CourseInfoVo courseInfoVo);

    CourseInfoVo getCourseInfo(String courseId);

    void updateCourseInfo(CourseInfoVo courseInfoVo);
}
