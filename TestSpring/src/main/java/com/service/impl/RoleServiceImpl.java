package com.service.impl;

import com.model.Role;
import com.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author jiHongYuan
 * @Title: RoleServiceImpl
 * @ProjectName TestSpring
 * @date 2019/3/2211:04
 */

@Service("roleService")
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleService roleService;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return roleService.deleteByPrimaryKey(id);
    }

    @Override
    public int insertSelective(Role record) {
        return roleService.insertSelective(record);
    }

    @Override
    public Role selectByPrimaryKey(Long id) {
        return roleService.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Role record) {
        return roleService.updateByPrimaryKeySelective(record);
    }
}
