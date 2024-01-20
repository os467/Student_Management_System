package pers.os467.management.entity;

import java.util.Date;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * (Student)表实体类
 *
 * @author makejava
 * @since 2023-12-02 19:50:33
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("serial")
public class Student {
    //学生号
    private Integer sid;
    //学生姓名
    private String sname;
    //学生所在班级号
    private Integer cid;
    //学生性别(1男,2女)
    private Integer sex;
    //出生日
    private Date birthday;

}

