package pers.os467.management.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.os467.management.annotion.AuthenticationList;
import pers.os467.management.base.ResponseUtils;
import pers.os467.management.base.response.PageListResponse;
import pers.os467.management.base.response.ResponseEntity;
import pers.os467.management.common.Constant;
import pers.os467.management.common.RoleEnum;
import pers.os467.management.entity.User;
import pers.os467.management.form.ChangePasswordForm;
import pers.os467.management.form.UpdateUserForm;
import pers.os467.management.service.RoleService;
import pers.os467.management.service.UserService;
import pers.os467.management.utils.CookieUtils;
import pers.os467.management.utils.JwtUser;
import pers.os467.management.utils.JwtUtils;
import pers.os467.management.utils.PageUtils;
import pers.os467.management.vo.GetUserByUidVo;
import pers.os467.management.vo.LoginUserVo;
import pers.os467.management.vo.UserVo;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;


@RestController
@RequestMapping("user")
public class UserController{

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    @SuppressWarnings("all")
    private HttpServletResponse httpServletResponse;

    @Autowired
    private HttpSession httpSession;

    @PostMapping("/login")
    public ResponseEntity loginUser(@RequestBody User user){
        User loginUser = userService.loginUser(user.getUsername(), user.getPassword());
        if (loginUser != null && loginUser.getUid() != null){
            //登录成功
            //整合登录用户信息
            JwtUser jwtUser = new JwtUser(loginUser.getUid(),
                    loginUser.getUsername(),
                    loginUser.getRid(),
                    roleService.getRoleByRid(loginUser.getRid()).getRname());
            //生成token
            String token = JwtUtils.generateToken(jwtUser);
            CookieUtils.setHttpOnlyCookieValue(httpServletResponse,Constant.TOKEN_COOKIE_NAME,token);

            //要返回的用户信息
            LoginUserVo loginUserVo = new LoginUserVo(jwtUser.getUid(), jwtUser.getUsername(), jwtUser.getRole(), loginUser.getAvatarUrl());

            return ResponseUtils.getSuccessResult(loginUserVo);
        }
        return ResponseUtils.getFailResult("用户名或密码错误");
    }

    @PostMapping("/logout")
    public ResponseEntity logoutUser(){
        CookieUtils.deleteCookie(httpServletResponse, Constant.TOKEN_COOKIE_NAME);
        return ResponseUtils.getSuccessResult();
    }

    @PostMapping("/changePassword")
    public ResponseEntity changePassword(@RequestBody ChangePasswordForm changePasswordForm){
        JwtUser jwtUser = (JwtUser)httpSession.getAttribute(Constant.JWT_USER);
        return userService.changePassword(changePasswordForm,jwtUser);
    }


    @AuthenticationList({RoleEnum.ADMIN})
    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody User user){

        int i = userService.addUser(user);

        if (i == -1){
            return ResponseUtils.getFailResult("存在同名用户");
        }

        if (i > 0){
            return ResponseUtils.getSuccessResult();
        }

        return ResponseUtils.getFailResult("操作失败");
    }

    @AuthenticationList({RoleEnum.ADMIN})
    @GetMapping("/getUserByUid")
    public ResponseEntity getUserByUid(Integer uid){
        GetUserByUidVo getUserByUidVo = userService.getUserByUid(uid);
        if (getUserByUidVo != null){
            return ResponseUtils.getSuccessResult(getUserByUidVo);
        }
        return ResponseUtils.getFailResult("未查询到用户信息");
    }

    @GetMapping("/searchAvatarUrl")
    public ResponseEntity searchAvatarUrl(String username){
        return userService.searchAvatarUrl(username);
    }

    @GetMapping("/getUserAvatarUrl")
    public ResponseEntity getUserAvatarUrl(){
        JwtUser jwtUser = (JwtUser)httpSession.getAttribute(Constant.JWT_USER);
        return userService.getUserAvatarUrl(jwtUser);
    }


    @AuthenticationList({RoleEnum.ADMIN})
    @PostMapping("/search")
    public ResponseEntity getUserListByLikeName(int currentPage, int pageSize, String likeName,String flag,Boolean precise){

        int total = userService.getUserListByLikeNameTotal(likeName,flag,precise);
        PageUtils pageUtils = new PageUtils(currentPage,pageSize,total);

        List<UserVo> userVoList = userService.getUserListByLikeName(pageUtils.getStartIndex(),pageSize,likeName,flag,precise);

        return ResponseUtils.getSuccessResult(new PageListResponse(userVoList,currentPage,pageSize,pageUtils.getLastPage(),total));
    }

    @AuthenticationList({RoleEnum.ADMIN})
    @PostMapping("/update")
    public ResponseEntity updateUser(@RequestBody UpdateUserForm user){

        int i = userService.updateUser(user);

        if (i > 0){
            return ResponseUtils.getSuccessResult();
        }

        return ResponseUtils.getFailResult("操作失败");
    }

    @AuthenticationList({RoleEnum.ADMIN})
    @PostMapping("/delete")
    public ResponseEntity deleteUserByUid(@RequestBody User user){

        int i = userService.deleteUserByUid(user.getUid());

        if (i > 0){
            return ResponseUtils.getSuccessResult();
        }

        return ResponseUtils.getFailResult("操作失败");
    }
}

