package pers.os467.management.service;

import pers.os467.management.entity.Menu;

import java.util.List;

public interface MenuService {
    /**
     * 获取到菜单(权限)列表
     * @return
     * @param rid
     */
    List<Menu> getMenuList(Integer rid);
}
