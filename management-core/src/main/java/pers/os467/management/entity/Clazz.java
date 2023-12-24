package pers.os467.management.entity;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * (Clazz)表实体类
 *
 * @author makejava
 * @since 2023-12-03 17:26:17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("serial")
public class Clazz {
    //班级号
    private Integer cid;
    //班级人数
    private Integer num;
    //班级所属年级
    private String grade;
    //班级名称
    private String clazzName;
}

