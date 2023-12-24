package pers.os467.management.entity;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * (Sk)表实体类
 *
 * @author makejava
 * @since 2023-12-07 23:01:34
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("serial")
public class SK {
    //授课记录号
    private Integer skId;
    //授课教师号
    private Integer tid;
    //授课课程号
    private Integer lid;

}

