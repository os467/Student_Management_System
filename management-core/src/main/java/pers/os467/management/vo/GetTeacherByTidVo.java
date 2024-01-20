package pers.os467.management.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("serial")
public class GetTeacherByTidVo {
    //教师号
    private Integer tid;
    //教师姓名
    private String tname;
    //性别(1男2女)
    private Integer sex;
    //教师出生日
    private String birthday;
}
