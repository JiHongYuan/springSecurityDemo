package com.service.impl;

import com.mapper.UserInfoMapper;
import com.model.User;
import com.model.UserInfo;
import com.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author jiHongYuan
 * @Title: UserServiceImpl
 * @ProjectName TestSpring
 * @date 2019/3/2211:05
 */
@Service("userInfoService")
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return userInfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insertSelective(User record) {
        return userInfoMapper.insertSelective(record);
    }

    @Override
    public User selectByPrimaryKey(Integer id) {
        return userInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(User record) {
        return userInfoMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public UserInfo getUserInfoByName(String username) {
        return userInfoMapper.getUserInfoByName(username);
    }
}
