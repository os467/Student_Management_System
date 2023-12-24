package pers.os467.management.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import pers.os467.management.entity.Role;

import java.util.List;

@Mapper
public interface RoleMapper {
    Role getRoleByRid(@Param("rid") Integer rid);

    List<Role> getRoleList();

}
