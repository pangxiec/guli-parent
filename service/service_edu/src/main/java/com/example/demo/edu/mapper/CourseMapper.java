package com.example.demo.edu.mapper;

import com.example.demo.edu.entity.Course;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.edu.entity.vo.CoursePublishVo;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author xmy
 * @since 2021-01-07
 */
public interface CourseMapper extends BaseMapper<Course> {

    public CoursePublishVo getPublishCourseInfo(String courseId);

}
