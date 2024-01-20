package pers.os467.management.vo;

import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("serial")
@Data
public class GetStudentBySidVo {
    //学生号
    private Integer sid;
    //学生姓名
    private String sname;
    //学生所在班级号
    private Integer cid;
    //学生性别(1男,2女)
    private Integer sex;
    //出生日
    private String birthday;
}
