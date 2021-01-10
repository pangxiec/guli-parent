package com.example.demo.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.edu.entity.Chapter;
import com.example.demo.edu.entity.Video;
import com.example.demo.edu.entity.course.ChapterVo;
import com.example.demo.edu.entity.course.VideoVo;
import com.example.demo.edu.mapper.ChapterMapper;
import com.example.demo.edu.mapper.VideoMapper;
import com.example.demo.edu.service.ChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.servicebase.exceptionhandler.GuliException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author xmy
 * @since 2021-01-07
 */
@Service
public class ChapterServiceImpl extends ServiceImpl<ChapterMapper, Chapter> implements ChapterService {

    @Resource
    private VideoMapper videoMapper;

    @Override
    public List<ChapterVo> getChapterVideoByCourseId(String courseId) {
        //根据课程id 查询出所有的章节
        QueryWrapper<Chapter> wrapperC = new QueryWrapper<>();
        wrapperC.eq("course_id",courseId);
        List<Chapter> chapters = baseMapper.selectList(wrapperC);

        //根据课程id 查询出所有的小节
        QueryWrapper<Video> wrapperV = new QueryWrapper<>();
        wrapperV.eq("course_id",courseId);
        List<Video> videos = videoMapper.selectList(wrapperV);

        //存放最终封装结果
        List<ChapterVo> chapterVoList = new ArrayList<>();

        //遍历课程章节List进行封装
        for (int i = 0; i < chapters.size(); i++) {
            Chapter chapter = chapters.get(i);
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(chapter,chapterVo);
            chapterVoList.add(chapterVo);

            //遍历小节List进行封装
            List<VideoVo> videoVos = new ArrayList<>();
            for (int j = 0; j < videos.size(); j++) {
                //得到每个小节
                Video video = videos.get(j);
                //小节里面的id和章节里面的id是否相同 相同才做封装
                if(video.getChapterId().equals(chapter.getId())){
                    VideoVo videoVo = new VideoVo();
                    BeanUtils.copyProperties(video,videoVo);
                    videoVos.add(videoVo);
                }
            }
            chapterVo.setChildren(videoVos);
        }
        return chapterVoList;
    }

    @Override
    public Boolean deleteChapter(String chapterId) {
        //根据章节id查询是否下面有小节
        QueryWrapper<Video> wrapper = new QueryWrapper<>();
        wrapper.eq("chapter_id",chapterId);
        Integer count = videoMapper.selectCount(wrapper);
        if (count > 0){
            //有小节不能删除
            throw new GuliException(20001,"不能删除");
        }else {
            int result = baseMapper.deleteById(chapterId);
            return result>0;
        }
    }
}
