package com.service;

import com.model.User;
import com.model.UserInfo;

/**
 * @author jiHongYuan
 * @Title: UserService
 * @ProjectName TestSpring
 * @date 2019/3/2211:03
 */
public interface UserInfoService {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    UserInfo getUserInfoByName(String username);
}
