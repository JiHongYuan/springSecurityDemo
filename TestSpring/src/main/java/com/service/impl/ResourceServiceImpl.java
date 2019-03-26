package com.service.impl;

import com.mapper.ResourceMapper;
import com.model.Resource;
import com.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author jiHongYuan
 * @Title: ResourceServiceImpl
 * @ProjectName TestSpring
 * @date 2019/3/2421:09
 */
@Service("resourceService")
public class ResourceServiceImpl implements ResourceService {
    @Autowired
    private ResourceMapper resourceMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return resourceMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insertSelective(Resource record) {
        return resourceMapper.insertSelective(record);
    }

    @Override
    public Resource selectByPrimaryKey(Long id) {
        return resourceMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Resource record) {
        return resourceMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<Resource> selectResourceRole() {
        return resourceMapper.selectResourceRole();
    }
}
