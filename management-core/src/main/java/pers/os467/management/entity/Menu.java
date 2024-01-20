package pers.os467.management.entity;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * (Menu)表实体类
 *
 * @author makejava
 * @since 2023-12-03 21:51:56
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("serial")
public class Menu {
    //菜单号
    private Integer mid;
    //菜单名称
    private String mname;
    //菜单对应页面的url
    private String url;
    //权限名
    private String permission;

}

