package pers.os467.management.service;

import pers.os467.management.entity.Role;

import java.util.List;

public interface RoleService {
    /**
     * 根据角色id获取角色名称
     * @param rid 角色id
     * @return 角色信息
     */
    Role getRoleByRid(Integer rid);

    /**
     * 获取角色列表
     * @return 角色列表
     */
    List<Role> getRoleList();
}
