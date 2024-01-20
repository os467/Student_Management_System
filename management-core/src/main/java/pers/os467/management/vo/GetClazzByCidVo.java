package pers.os467.management.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("serial")
public class GetClazzByCidVo {
    //班级号
    private Integer cid;
    //班级人数
    private Integer num;
    //班级所属年级
    private String grade;
    //班级名称
    private String clazzName;
}
