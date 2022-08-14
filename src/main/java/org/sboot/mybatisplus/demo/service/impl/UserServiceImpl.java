package org.sboot.mybatisplus.demo.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.sboot.mybatisplus.demo.pojo.User;
import org.sboot.mybatisplus.demo.service.UserService;
import org.sboot.mybatisplus.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 */
@DS("master")
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Override
    public Page<User> selectPageBySelf(Page<User> page, User wrapper) {
        return userMapper.selectPageBySelf(page, wrapper);
    }
}




