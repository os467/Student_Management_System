package pers.os467.management.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("serial")
@Data
public class GetSKBySKIdVo {
    //授课记录号
    private Integer skId;
    //授课教师名
    private String tname;
    //授课课程名
    private String lname;
    //教师号
    private Integer tid;
    //课程号
    private Integer lid;
}
