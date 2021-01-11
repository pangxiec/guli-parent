package com.example.demo.edu.controller;


import com.example.common.R;
import com.example.demo.edu.entity.Video;
import com.example.demo.edu.service.VideoService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author xmy
 * @since 2021-01-07
 */
@RestController
@RequestMapping("eduservice/video")
@CrossOrigin
public class VideoController {

    @Resource
    private VideoService videoService;

    @PostMapping("/addVideo")
    public R addVideo(@RequestBody Video video){
        videoService.save(video);
        return R.ok();
    }

    /**
     * TODO 删除小节的时候要把里面的视频也删除
     * @param id
     * @return
     */
    @PostMapping("/deleteVideo/{id}")
    public R deleteVideo(@PathVariable String id){
        videoService.removeById(id);
        return R.ok();
    }



}

