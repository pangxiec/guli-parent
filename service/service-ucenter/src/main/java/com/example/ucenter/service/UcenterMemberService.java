package com.example.ucenter.service;

import com.example.ucenter.RegisterVo;
import com.example.ucenter.entity.UcenterMember;
import com.baomidou.mybatisplus.extension.service.IService;
/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author xmy
 * @since 2021-01-19
 */
public interface UcenterMemberService extends IService<UcenterMember> {

    String login(UcenterMember ucenterMember);

    void register(RegisterVo registerVo);

    Integer countRegisterDay(String day);
}
