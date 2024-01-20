package pers.os467.management.entity;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * (Xk)表实体类
 *
 * @author makejava
 * @since 2023-12-08 14:50:54
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("serial")
public class XK {
    //选课号
    private Integer xkId;
    //选课所属学生号
    private Integer sid;
    //选课所属授课号
    private Integer skId;
    //学期
    private String term;
    //分数
    private String score;

}

