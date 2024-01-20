package pers.os467.management.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.os467.management.entity.Menu;
import pers.os467.management.mapper.MenuMapper;
import pers.os467.management.service.MenuService;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> getMenuList(Integer rid) {
        return menuMapper.getMenuList(rid);
    }
}
