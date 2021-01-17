package com.example.demo.edu.client;

import com.example.common.R;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author xmy
 * @data 2021/1/17 23:32
 */
@Component
public class VodFeignClient implements VodClient {

    @Override
    public R removeAlyVideo(String id) {
        return R.error().message("删除视频出错了...");
    }

    @Override
    public R deleteBatch(List<String> videoIdList) {
        return R.error().message("删除多个视频出错了...");
    }
}
