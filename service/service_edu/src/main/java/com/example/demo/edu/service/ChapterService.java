package com.example.demo.edu.service;

import com.example.demo.edu.entity.Chapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.edu.entity.course.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author xmy
 * @since 2021-01-07
 */
public interface ChapterService extends IService<Chapter> {

    /**
     * 课程大纲列表 根据课程Id查询
     * @param courseId
     * @return
     */
    List<ChapterVo> getChapterVideoByCourseId(String courseId);

    Boolean deleteChapter(String chapterId);

    void removeChapterByCourseId(String courseId);
}
