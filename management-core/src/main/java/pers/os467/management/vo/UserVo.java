package pers.os467.management.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("serial")
@Data
public class UserVo {
    //用户号
    private Integer uid;
    //用户名
    private String username;
    //用户所属角
    private String role;
}
