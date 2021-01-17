package com.example.demo.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.edu.client.VodClient;
import com.example.demo.edu.entity.Video;
import com.example.demo.edu.mapper.VideoMapper;
import com.example.demo.edu.service.VideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author xmy
 * @since 2021-01-07
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {

    @Resource
    private VodClient vodClient;

    @Override
    public void removeVideoByCourseId(String courseId) {
        QueryWrapper<Video> queryWrapper = new QueryWrapper<>();
        //根据课程id查询出视频id
        queryWrapper.eq("course_id",courseId);
        //指定查询某个字段
        queryWrapper.select("video_source_id");
        List<Video> videos = baseMapper.selectList(queryWrapper);

        List<String> videoIds = new ArrayList<>();
        for (int i = 0; i < videos.size(); i++) {
            Video video = videos.get(i);
            String videoSourceId = video.getVideoSourceId();
            if (!StringUtils.isEmpty(videoSourceId)){
                videoIds.add(videoSourceId);
            }
        }

        //删除多个视频
        if (videoIds.size() > 0){
            vodClient.deleteBatch(videoIds);
        }

        QueryWrapper<Video> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        baseMapper.delete(wrapper);
    }
}
