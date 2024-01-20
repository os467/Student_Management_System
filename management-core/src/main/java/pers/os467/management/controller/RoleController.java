package pers.os467.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.os467.management.annotion.AuthenticationList;
import pers.os467.management.base.ResponseUtils;
import pers.os467.management.base.response.ResponseEntity;
import pers.os467.management.common.RoleEnum;
import pers.os467.management.entity.Role;
import pers.os467.management.service.RoleService;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @AuthenticationList({RoleEnum.ADMIN})
    @GetMapping("/list")
    public ResponseEntity getRoleList(){
        List<Role> roleList = roleService.getRoleList();
        return ResponseUtils.getSuccessResult(roleList);
    }

}
