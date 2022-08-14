package org.sboot.mybatisplus.demo.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.sboot.mybatisplus.demo.pojo.Product;
import org.sboot.mybatisplus.demo.service.ProductService;
import org.sboot.mybatisplus.demo.mapper.ProductMapper;
import org.springframework.stereotype.Service;

/**
 *
 */
@DS("slave_1")
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product>
    implements ProductService{

}




