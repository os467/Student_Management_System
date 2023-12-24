package pers.os467.management.entity;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * (User)表实体类
 *
 * @author makejava
 * @since 2023-12-04 23:30:25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("serial")
public class User {
    //用户号
    private Integer uid;
    //用户名
    private String username;
    //密码
    private String password;
    //用户所属角色号
    private Integer rid;
    //用户头像地址
    private String avatarUrl;
}

