package com.example.msm.controller;

import com.example.common.R;
import com.example.msm.service.MsmService;
import com.example.msm.utils.RandomUtil;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author xmy
 * @data 2021/1/19 20:20
 */
@RestController
@RequestMapping("/edumsm/msm")
@CrossOrigin
public class MsmController {

    @Resource
    private MsmService msmService;

    @Resource
    private RedisTemplate<String,String> redisTemplate;

    @ApiOperation("发送短信的方法")
    @GetMapping("/send/{phone}")
    public R sendMsm(@PathVariable("phone") String phone){
        //从redis中取出验证码
        String code = redisTemplate.opsForValue().get(phone);
        if (!StringUtils.isEmpty(code)){
            return R.ok();
        }
        //如果没取到在进行发送验证码
        //生成随机数 传递到阿里云进行发送
        code = RandomUtil.getFourBitRandom();
        Map<String,Object> param = new HashMap<>();
        param.put("code",code);
        //调用service中发送短信的服务
        boolean isSend = msmService.sendMsm(param,phone);
        if (isSend){
            //发送成功后存储到redis中 并设置有效时间
            redisTemplate.opsForValue().set(phone,code,5, TimeUnit.MINUTES);
            return R.ok();
        }
        return R.error().message("短信发送失败");
    }

}
