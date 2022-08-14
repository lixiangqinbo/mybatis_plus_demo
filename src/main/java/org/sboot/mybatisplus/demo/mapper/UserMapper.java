package org.sboot.mybatisplus.demo.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.sboot.mybatisplus.demo.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * @Entity org.sboot.mybatisplus.demo.pojo.User
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

    /**
     * 自自定义分页
     */
   public Page<User> selectPageBySelf(@Param("page")Page<User> page, @Param("wrapper")User wrapper);
}




