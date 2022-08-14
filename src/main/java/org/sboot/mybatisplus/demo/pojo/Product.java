package org.sboot.mybatisplus.demo.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName t_product
 */
@TableName(value ="product")
@Data
public class Product implements Serializable {
    /**
     * 主键ID
     */
    @TableId
    private Long id;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 价格
     */
    private Integer price;

    /**
     * 乐观锁版本号
     */
    @Version
    private Integer version;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}