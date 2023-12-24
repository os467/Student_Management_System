package pers.os467.management.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("serial")
public class UpdateUserForm {
    //用户号
    private Integer uid;
    //用户名
    private String username;
    //是否重置密码
    private Boolean resetPassword;
    //用户所属角色号
    private Integer rid;
}
