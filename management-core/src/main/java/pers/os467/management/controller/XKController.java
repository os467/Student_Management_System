package pers.os467.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.os467.management.annotion.AuthenticationList;
import pers.os467.management.base.ResponseUtils;
import pers.os467.management.base.response.PageListResponse;
import pers.os467.management.base.response.ResponseEntity;
import pers.os467.management.common.Constant;
import pers.os467.management.common.RoleEnum;
import pers.os467.management.entity.XK;
import pers.os467.management.service.XKService;
import pers.os467.management.utils.JwtUser;
import pers.os467.management.utils.PageUtils;
import pers.os467.management.vo.GetXKByXKIdVo;
import pers.os467.management.vo.ScoreVo;
import pers.os467.management.vo.XKVo;

import javax.servlet.http.HttpSession;
import java.util.List;


@RestController
@RequestMapping("/xk")
public class XKController {

    @Autowired
    private XKService xkService;

    @Autowired
    private HttpSession session;

    @AuthenticationList({RoleEnum.ADMIN,RoleEnum.TEACHER,RoleEnum.STUDENT})
    @PostMapping("/add")
    public ResponseEntity addXK(@RequestBody XK xk){
        return xkService.addXK(xk);
    }

    @AuthenticationList({RoleEnum.ADMIN,RoleEnum.TEACHER,RoleEnum.STUDENT})
    @GetMapping("/getXKByXKId")
    public ResponseEntity getXKByXKId(Integer xkId){
        GetXKByXKIdVo getXKByXKIdVo = xkService.getXKByXKId(xkId);
        if (getXKByXKIdVo != null){
            return ResponseUtils.getSuccessResult(getXKByXKIdVo);
        }
        return ResponseUtils.getFailResult("未查询到选课信息");
    }

    @AuthenticationList({RoleEnum.ADMIN,RoleEnum.TEACHER,RoleEnum.STUDENT})
    @PostMapping("/search")
    public ResponseEntity getXKListByLikeName(int currentPage, int pageSize, String likeName,String flag,Boolean precise){

        JwtUser jwtUser = (JwtUser)session.getAttribute(Constant.JWT_USER);

        int total = xkService.getXKListByLikeNameTotal(likeName,flag,precise,jwtUser);
        PageUtils pageUtils = new PageUtils(currentPage,pageSize,total);

        List<XKVo> xkVoList = xkService.getXKListByLikeName(pageUtils.getStartIndex(),pageSize,
                likeName,flag,precise,
                jwtUser);

        return ResponseUtils.getSuccessResult(new PageListResponse(xkVoList,currentPage,pageSize,pageUtils.getLastPage(),total));
    }

    @AuthenticationList({RoleEnum.ADMIN,RoleEnum.TEACHER})
    @PostMapping("/update")
    public ResponseEntity updateXK(@RequestBody XK xk){

        int i = xkService.updateXK(xk);

        if (i > 0){
            return ResponseUtils.getSuccessResult();
        }

        return ResponseUtils.getFailResult("操作失败");
    }

    @AuthenticationList({RoleEnum.ADMIN,RoleEnum.TEACHER})
    @PostMapping("/delete")
    public ResponseEntity deleteXKByXKId(@RequestBody XK xk){

        int i = xkService.deleteXKByXKId(xk.getXkId());

        if (i > 0){
            return ResponseUtils.getSuccessResult();
        }

        return ResponseUtils.getFailResult("操作失败");
    }

    @AuthenticationList({RoleEnum.ADMIN,RoleEnum.TEACHER})
    @PostMapping("/scoreByClazz")
    public ResponseEntity getScoreByClazzResult(String likeName, String skId,String term,String filterFlag){

        ScoreVo scoreVo = xkService.getScoreByClazzResult(likeName,skId, term,filterFlag);

        return ResponseUtils.getSuccessResult(scoreVo);
    }

    @AuthenticationList({RoleEnum.ADMIN,RoleEnum.TEACHER,RoleEnum.STUDENT})
    @PostMapping("/score")
    public ResponseEntity getScoreResult(String likeName, String flag,String term,String filterFlag){

        ScoreVo scoreVo = xkService.getScoreResult(likeName,flag, term,filterFlag);

        return ResponseUtils.getSuccessResult(scoreVo);
    }

}
