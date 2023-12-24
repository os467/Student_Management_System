package pers.os467.management.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("serial")
@Data
public class GetUserByUidVo {
    //用户号
    private Integer uid;
    //用户名
    private String username;
    //用户所属角色号
    private Integer rid;
}
