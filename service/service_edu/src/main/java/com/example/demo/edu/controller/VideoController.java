package com.example.demo.edu.controller;


import com.example.common.R;
import com.example.demo.edu.client.VodClient;
import com.example.demo.edu.entity.Video;
import com.example.demo.edu.service.VideoService;
import org.apache.commons.lang3.StringUtils;
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

    @Resource
    private VodClient vodClient;

    @PostMapping("/addVideo")
    public R addVideo(@RequestBody Video video){
        videoService.save(video);
        return R.ok();
    }

    /**
     * @param id
     * @return
     */
    @PostMapping("/deleteVideo/{id}")
    public R deleteVideo(@PathVariable String id){
        //删除视频是根据视频id删除的，首先得到视频id
        Video video = videoService.getById(id);
        String videoSourceId = video.getVideoSourceId();
        if (!StringUtils.isEmpty(videoSourceId)){
            vodClient.removeAlyVideo(videoSourceId);
        }
        videoService.removeById(id);
        return R.ok();
    }



}

