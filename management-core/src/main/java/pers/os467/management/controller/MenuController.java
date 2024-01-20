package pers.os467.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.os467.management.base.ResponseUtils;
import pers.os467.management.base.response.ResponseEntity;
import pers.os467.management.common.Constant;
import pers.os467.management.entity.Menu;
import pers.os467.management.service.MenuService;
import pers.os467.management.utils.JwtUser;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @Autowired
    private HttpSession session;

    @GetMapping("/list")
    public ResponseEntity getMenuList(){

        JwtUser jwtUser = (JwtUser) session.getAttribute(Constant.JWT_USER);

        List<Menu> menuList = menuService.getMenuList(jwtUser.getRid());

        return ResponseUtils.getSuccessResult(menuList);
    }

}
