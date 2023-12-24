package pers.os467.management.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import pers.os467.management.entity.Menu;

import java.util.List;

@Mapper
public interface MenuMapper {
    List<Menu> getMenuList(@Param("rid") Integer rid);
}
