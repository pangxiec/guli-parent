package com.example.demo.edu.controller;


import com.example.common.R;
import com.example.demo.edu.entity.Chapter;
import com.example.demo.edu.entity.course.ChapterVo;
import com.example.demo.edu.service.ChapterService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author xmy
 * @since 2021-01-07
 */
@RestController
@RequestMapping("/eduservice/chapter")
@CrossOrigin
public class ChapterController {

    @Resource
    ChapterService chapterService;

    @GetMapping("/getChapterVideo/{courseId}")
    public R getChapterVideo(@PathVariable String courseId){
        List<ChapterVo> list = chapterService.getChapterVideoByCourseId(courseId);
        return R.ok().data("allChapterVideo",list);
    }

    @PostMapping("/addChapter")
    public R addChapter(@RequestBody Chapter chapter){
        chapterService.save(chapter);
        return R.ok();
    }

    //根据Id 查询信息
    @GetMapping("/getChapterInfo/{chapterId}")
    public R getChapterInfo(@PathVariable String chapterId){
        Chapter chapter = chapterService.getById(chapterId);
        return R.ok().data("chapter",chapter);
    }

    @PostMapping("/updateChapter")
    public R updateChapter(@RequestBody Chapter chapter){
        chapterService.updateById(chapter);
        return R.ok();
    }

    @PostMapping("/deleteChapter/{courseId}")
    public R deleteChapter(@PathVariable String chapterId){
        Boolean result = chapterService.deleteChapter(chapterId);
        if (result){
            return R.ok();
        }else {
            return R.error();
        }
    }

}

