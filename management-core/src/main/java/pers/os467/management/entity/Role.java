package pers.os467.management.entity;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * (Role)表实体类
 *
 * @author makejava
 * @since 2023-12-04 23:52:24
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("serial")
public class Role {
    //角色号
    private Integer rid;
    //角色名称
    private String rname;

}

