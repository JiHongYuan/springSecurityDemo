package com.service;

import com.model.Resource;

import java.util.List;

/**
 * @author jiHongYuan
 * @Title: ResourseService
 * @ProjectName TestSpring
 * @date 2019/3/2421:02
 */
public interface ResourceService {
    int deleteByPrimaryKey(Long id);

    int insertSelective(Resource record);

    Resource selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Resource record);

    List<Resource> selectResourceRole();
}
