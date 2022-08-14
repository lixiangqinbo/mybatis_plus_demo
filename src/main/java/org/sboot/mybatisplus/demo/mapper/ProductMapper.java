package org.sboot.mybatisplus.demo.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.sboot.mybatisplus.demo.pojo.Product;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * @Entity org.sboot.mybatisplus.demo.pojo.Product
 */
@Repository
//@DS("slave_1")
public interface ProductMapper extends BaseMapper<Product> {

}




