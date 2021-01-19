package com.example.msm.service;

import java.util.Map;

/**
 * @author xmy
 * @data 2021/1/19 20:20
 */
public interface MsmService {

    /**
     * 发送短信
     * @param param
     * @param phone
     * @return
     */
    boolean sendMsm(Map<String, Object> param, String phone);
}
