package com.example.demo.edu.service.impl;

import com.example.demo.edu.entity.Course;
import com.example.demo.edu.entity.CourseDescription;
import com.example.demo.edu.entity.vo.CourseInfoVo;
import com.example.demo.edu.entity.vo.CoursePublishVo;
import com.example.demo.edu.mapper.CourseDescriptionMapper;
import com.example.demo.edu.mapper.CourseMapper;
import com.example.demo.edu.service.ChapterService;
import com.example.demo.edu.service.CourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.edu.service.VideoService;
import com.example.servicebase.exceptionhandler.GuliException;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
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
    private CourseDescriptionMapper descriptionMapper;

    @Resource
    private VideoService videoService;

    @Resource
    private ChapterService chapterService;

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

    @Override
    public CourseInfoVo getCourseInfo(String courseId) {
        //查询课程表信息
        Course course = baseMapper.selectById(courseId);
        CourseInfoVo courseInfoVo = new CourseInfoVo();
        BeanUtils.copyProperties(course,courseInfoVo);

        //查询描述表
        CourseDescription courseDescription = descriptionMapper.selectById(courseId);
        courseInfoVo.setDescription(courseDescription.getDescription());

        return courseInfoVo;
    }

    @Override
    public void updateCourseInfo(CourseInfoVo courseInfoVo) {
        //修改课程表中的信息
        Course course = new Course();
        BeanUtils.copyProperties(courseInfoVo,course);
        int update = baseMapper.updateById(course);
        if (update == 0){
            throw new GuliException(20001,"修改课程信息失败");
        }

        //修改描述表中的信息
        CourseDescription courseDescription = new CourseDescription();
        courseDescription.setId(courseInfoVo.getId());
        courseDescription.setDescription(courseInfoVo.getDescription());
        descriptionMapper.updateById(courseDescription);
    }

    @Override
    public CoursePublishVo publishCourseInfo(String id) {
        CoursePublishVo publishCourseInfo = baseMapper.getPublishCourseInfo(id);
        return publishCourseInfo;
    }

    @Override
    public void removeCourse(String courseId) {
        //根据课程id删除小节
        videoService.removeVideoByCourseId(courseId);

        //根据课程id删除章节
        chapterService.removeChapterByCourseId(courseId);

        //根据课程id删除描述
        descriptionMapper.deleteById(courseId);

        //根据课程id删除课程
        int result = baseMapper.deleteById(courseId);
        if (result == 0){
            throw new GuliException(20001,"删除课程失败");
        }
    }

}
