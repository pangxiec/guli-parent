package com.example.demo.edu.client;

import com.example.common.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author xmy
 * @data 2021/1/17 14:58
 */
@FeignClient(name = "service-vod",fallback = VodFeignClient.class)
@Component
public interface VodClient {

    /**
     * 定义调用方法的路径 路径是完全路径
     * @param id
     * @return
     */
    @PostMapping("/eduvod/video/removeAlyVideo/{id}")
    public R removeAlyVideo(@PathVariable("id") String id);

    @PostMapping("/delete-batch")
    public R deleteBatch(@RequestParam("videoIdList") List<String> videoIdList);
}
