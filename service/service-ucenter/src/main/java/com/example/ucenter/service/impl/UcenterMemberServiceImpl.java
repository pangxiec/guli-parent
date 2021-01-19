package com.example.ucenter.service.impl;

import com.example.ucenter.entity.UcenterMember;
import com.example.ucenter.mapper.UcenterMemberMapper;
import com.example.ucenter.service.UcenterMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author xmy
 * @since 2021-01-19
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {

    @Override
    public String login(UcenterMember ucenterMember) {
        return null;
    }
}
