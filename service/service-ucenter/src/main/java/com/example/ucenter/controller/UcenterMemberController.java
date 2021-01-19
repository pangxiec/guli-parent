package com.example.ucenter.controller;


import com.example.common.R;
import com.example.ucenter.entity.UcenterMember;
import com.example.ucenter.mapper.UcenterMemberMapper;
import com.example.ucenter.service.UcenterMemberService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author xmy
 * @since 2021-01-19
 */
@RestController
@RequestMapping("/educenter/member")
@CrossOrigin
public class UcenterMemberController {

    @Resource
    UcenterMemberService ucenterMemberService;

    @GetMapping("/login")
    public R loginUser(@RequestBody UcenterMember ucenterMember){
        //调用service中方法实现登录
        //返回token,使jwt生成
        String token = ucenterMemberService.login(ucenterMember);
        return R.ok().data("token",token);
    }

}

