package pers.os467.management.entity;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * (Lesson)表实体类
 *
 * @author makejava
 * @since 2023-12-07 22:01:12
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("serial")
public class Lesson {
    //课程号
    private Integer lid;
    //课程名
    private String lname;
    //课程学时
    private String hours;

}

