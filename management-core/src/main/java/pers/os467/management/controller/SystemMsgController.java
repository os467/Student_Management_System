package pers.os467.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.os467.management.annotion.AuthenticationList;
import pers.os467.management.base.response.ResponseEntity;
import pers.os467.management.common.Constant;
import pers.os467.management.common.RoleEnum;
import pers.os467.management.form.SystemMsgForm;
import pers.os467.management.service.SystemMsgReadService;
import pers.os467.management.service.SystemMsgService;
import pers.os467.management.utils.JwtUser;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/systemMsg")
public class SystemMsgController {

    @Autowired
    private SystemMsgService systemMsgService;

    @Autowired
    private SystemMsgReadService systemMsgReadService;

    @Autowired
    private HttpSession httpSession;

    @AuthenticationList({RoleEnum.ADMIN})
    @PostMapping("/add")
    public ResponseEntity addSystemMsg(@RequestBody SystemMsgForm systemMsgForm, Integer msgId){
        JwtUser jwtUser = (JwtUser)httpSession.getAttribute(Constant.JWT_USER);
        return systemMsgReadService.addSystemMsg(systemMsgForm,jwtUser);
    }

    @PostMapping("/delete")
    public ResponseEntity deleteSystemMsg(Integer msgReadId){
        JwtUser jwtUser = (JwtUser)httpSession.getAttribute(Constant.JWT_USER);
        return systemMsgReadService.deleteSystemMsg(msgReadId,jwtUser);
    }

    @PostMapping("/read")
    public ResponseEntity readSystemMsg(Integer msgReadId){
        JwtUser jwtUser = (JwtUser)httpSession.getAttribute(Constant.JWT_USER);
        return systemMsgReadService.readSystemMsg(msgReadId,jwtUser);
    }

    @GetMapping("/list")
    public ResponseEntity getSystemMsgList(){
        JwtUser jwtUser = (JwtUser)httpSession.getAttribute(Constant.JWT_USER);
        return systemMsgService.getSystemMsgList(jwtUser);
    }

}
