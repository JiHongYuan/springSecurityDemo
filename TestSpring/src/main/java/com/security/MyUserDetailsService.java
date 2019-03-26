package com.security;

import com.model.UserInfo;
import com.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @author jiHongYuan
 * @Title: CustomizedUserDetailsService
 * @ProjectName TestSpring
 * @date 2019/3/2122:04
 */
@Component
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserInfoService userInfoService;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserInfo userInfo = userInfoService.getUserInfoByName(s);
        return userInfo;
    }

}
