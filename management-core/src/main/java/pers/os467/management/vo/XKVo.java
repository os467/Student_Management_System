package pers.os467.management.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("serial")
public class XKVo {
    //选课号
    private Integer xkId;
    //选课所属学生名
    private String sname;
    //授课编号
    private Integer skId;
    //选课所属授课课程名
    private String lname;
    //选课所属授课教师名
    private String tname;
    //学期
    private String term;
    //分数
    private String score;
}
