package com.example.demo.edu.entity.course;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xmy
 * @data 2021/1/10 13:50
 */
@Data
public class ChapterVo {

    private String id;

    private String title;

    private List<VideoVo> children = new ArrayList<>();
}

