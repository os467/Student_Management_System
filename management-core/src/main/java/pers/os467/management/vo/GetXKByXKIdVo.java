package pers.os467.management.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("serial")
@Data
public class GetXKByXKIdVo {
    //选课号
    private Integer xkId;
    //选课所属学生名
    private String sname;
    //选课所属授课课程名
    private String lname;
    //选课所属授课教师名
    private String tname;
    //学期
    private String term;
    //分数
    private String score;
    //学生号
    private Integer sid;
    //授课号
    private Integer skId;
}
