package pers.os467.management.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("serial")
public class SKVo {
    //授课记录号
    private Integer skId;
    //授课教师名
    private String tname;
    //授课课程名
    private String lname;
}
