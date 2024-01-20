package pers.os467.management.entity;

import java.util.Date;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * (Teacher)表实体类
 *
 * @author makejava
 * @since 2023-12-04 01:07:32
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("serial")
public class Teacher {
    //教师号
    private Integer tid;
    //教师姓名
    private String tname;
    //性别(1男2女)
    private Integer sex;
    //教师出生日
    private Date birthday;

}

