package org.sboot.mybatisplus.demo.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import lombok.Data;
import org.sboot.mybatisplus.demo.pojoenum.SexEnum;

/**
 * 
 * @TableName user
 */
@TableName(value ="user")
@Data
public class User implements Serializable {
    /**
     * 主键ID
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 邮箱
     */
    private String email;
    /**
     * 逻辑删除
     */
    @TableLogic
    private int isDeleted;
    /**
     * 性别
     */
    private SexEnum sex;
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}