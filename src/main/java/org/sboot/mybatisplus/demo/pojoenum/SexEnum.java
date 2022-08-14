package org.sboot.mybatisplus.demo.pojoenum;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

@Getter
public enum SexEnum {
    MALE(1,"男"),
    FEMALE(0,"女");
    @EnumValue
    private int sex;
    private String sexName;

    SexEnum(int sex,String sexName){
        this.sex=sex;
        this.sexName =sexName;

    }
}
