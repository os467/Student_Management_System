package pers.os467.management.vo;
import java.util.Date;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("serial")
@Data
public class StudentVo {
    //学生号
    private Integer sid;
    //学生姓名
    private String sname;
    //学生所在班级号
    private Integer cid;
    //学生所在班级
    private String clazz;
    //学生性别(1男,2女)
    private String sex;
    //出生日
    private String birthday;
}
