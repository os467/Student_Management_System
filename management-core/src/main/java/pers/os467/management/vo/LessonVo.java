package pers.os467.management.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("serial")
@Data
public class LessonVo {
    //课程号
    private Integer lid;
    //课程名
    private String lname;
    //课程学时
    private String hours;

}
