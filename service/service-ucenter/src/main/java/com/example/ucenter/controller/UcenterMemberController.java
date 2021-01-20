package com.example.ucenter.controller;


import com.example.common.JwtUtils;
import com.example.common.R;
import com.example.ucenter.RegisterVo;
import com.example.ucenter.entity.UcenterMember;
import com.example.ucenter.mapper.UcenterMemberMapper;
import com.example.ucenter.service.UcenterMemberService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
    UcenterMemberService memberService;

    @GetMapping("/login")
    public R loginUser(@RequestBody UcenterMember ucenterMember){
        //调用service中方法实现登录
        //返回token,使jwt生成
        String token = memberService.login(ucenterMember);
        return R.ok().data("token",token);
    }

    @PostMapping("/register")
    public R registerUser(@RequestBody RegisterVo registerVo) {
        memberService.register(registerVo);
        return R.ok();
    }

    //根据token获取用户信息
    @GetMapping("/getMemberInfo")
    public R getMemberInfo(HttpServletRequest request) {
        //调用jwt工具类的方法。根据request对象获取头信息，返回用户id
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        //查询数据库根据用户id获取用户信息
        UcenterMember member = memberService.getById(memberId);
        return R.ok().data("userInfo",member);
    }

    @GetMapping("/countRegister/{day}")
    public R countRegister(@PathVariable("day") String day){
        int count = memberService.countRegisterDay(day);
        return R.ok().data("count",count);
    }

}

