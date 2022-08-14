package org.sboot.mybatisplus.demo.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.sboot.mybatisplus.demo.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 *
 */
public interface UserService extends IService<User> {

    public Page<User> selectPageBySelf(Page<User> page, User wrapper);
}
