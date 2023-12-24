package pers.os467.management.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.os467.management.entity.Role;
import pers.os467.management.mapper.RoleMapper;
import pers.os467.management.service.RoleService;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Role getRoleByRid(Integer rid) {
        return roleMapper.getRoleByRid(rid);
    }

    @Override
    public List<Role> getRoleList() {
        return roleMapper.getRoleList();
    }
}
