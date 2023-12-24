package pers.os467.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.os467.management.annotion.AuthenticationList;
import pers.os467.management.base.ResponseUtils;
import pers.os467.management.base.response.PageListResponse;
import pers.os467.management.base.response.ResponseEntity;
import pers.os467.management.common.RoleEnum;
import pers.os467.management.entity.SK;
import pers.os467.management.service.SKService;
import pers.os467.management.utils.PageUtils;
import pers.os467.management.vo.GetSKBySKIdVo;
import pers.os467.management.vo.SKVo;

import java.util.List;


@RestController
@RequestMapping("/sk")
public class SKController {

    @Autowired
    private SKService skService;

    @AuthenticationList({RoleEnum.ADMIN,RoleEnum.TEACHER})
    @PostMapping("/add")
    public ResponseEntity addSK(@RequestBody SK sk){
        return skService.addSK(sk);
    }

    @AuthenticationList({RoleEnum.ADMIN,RoleEnum.TEACHER,RoleEnum.STUDENT})
    @GetMapping("/getSKBySKId")
    public ResponseEntity getSKBySKId(Integer skId){
        GetSKBySKIdVo getSKBySKIdVo = skService.getSKBySKId(skId);
        if (getSKBySKIdVo != null){
            return ResponseUtils.getSuccessResult(getSKBySKIdVo);
        }
        return ResponseUtils.getFailResult("未查询到授课信息");
    }

    @AuthenticationList({RoleEnum.ADMIN,RoleEnum.TEACHER,RoleEnum.STUDENT})
    @PostMapping("/search")
    public ResponseEntity getSKListByLikeName(int currentPage, int pageSize, String likeName,String flag,Boolean precise){

        int total = skService.getSKListByLikeNameTotal(likeName,flag,precise);
        PageUtils pageUtils = new PageUtils(currentPage,pageSize,total);

        List<SKVo> skVoList = skService.getSKListByLikeName(pageUtils.getStartIndex(),pageSize,likeName,flag,precise);

        return ResponseUtils.getSuccessResult(new PageListResponse(skVoList,currentPage,pageSize,pageUtils.getLastPage(),total));
    }

    @AuthenticationList({RoleEnum.ADMIN,RoleEnum.TEACHER})
    @PostMapping("/update")
    public ResponseEntity updateSK(@RequestBody SK sk){

        int i = skService.updateSK(sk);

        if (i > 0){
            return ResponseUtils.getSuccessResult();
        }

        return ResponseUtils.getFailResult("操作失败");
    }

    @AuthenticationList({RoleEnum.ADMIN,RoleEnum.TEACHER})
    @PostMapping("/delete")
    public ResponseEntity deleteSKBySKId(@RequestBody SK sk){

        ResponseEntity responseEntity = skService.deleteSKBySKId(sk.getSkId());

        return responseEntity;
    }

}
