package com.example.demo.edu.controller;

import com.example.common.R;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

/**
 * @author xmy
 * @date 2020/12/30 11:53
 */
@Api(description = "登录管理")
@RestController
@RequestMapping("/eduservice/user")
@CrossOrigin
public class LoginController {

    @PostMapping("/login")
    public R login(){
        return R.ok().data("token","admin");
    }

    @GetMapping("/info")
    public R info(){
        return R.ok().data("roles","[admin]").data("name","admin").data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }

}
