package pers.os467.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.os467.management.annotion.AuthenticationList;
import pers.os467.management.base.ResponseUtils;
import pers.os467.management.base.response.PageListResponse;
import pers.os467.management.base.response.ResponseEntity;
import pers.os467.management.common.RoleEnum;
import pers.os467.management.entity.Clazz;
import pers.os467.management.service.ClazzService;
import pers.os467.management.utils.PageUtils;
import pers.os467.management.vo.GetClazzByCidVo;
import pers.os467.management.vo.ClazzVo;

import java.util.List;

@RestController
@RequestMapping("/clazz")
public class ClazzController {

    @Autowired
    private ClazzService clazzService;

    @AuthenticationList({RoleEnum.ADMIN,RoleEnum.TEACHER,RoleEnum.STUDENT})
    @GetMapping("/list")
    public ResponseEntity getClazzList(){
        List<Clazz> clazzList = clazzService.getClazzList();
        return ResponseUtils.getSuccessResult(clazzList);
    }

    @AuthenticationList({RoleEnum.ADMIN,RoleEnum.TEACHER})
    @PostMapping("/add")
    public ResponseEntity addClazz(@RequestBody Clazz clazz){

        int i = clazzService.addClazz(clazz);

        if (i > 0){
            return ResponseUtils.getSuccessResult();
        }

        return ResponseUtils.getFailResult("操作失败");
    }

    @AuthenticationList({RoleEnum.ADMIN,RoleEnum.TEACHER,RoleEnum.STUDENT})
    @GetMapping("/getClazzByCid")
    public ResponseEntity getClazzByCid(Integer cid){
        Clazz clazz = clazzService.getClazzByCid(cid);
        GetClazzByCidVo getClazzByCidVo = new GetClazzByCidVo(clazz.getCid(),
                clazz.getNum(), clazz.getGrade(), clazz.getClazzName());
        if (getClazzByCidVo != null){
            return ResponseUtils.getSuccessResult(getClazzByCidVo);
        }
        return ResponseUtils.getFailResult("未查询到班级信息");
    }

    @AuthenticationList({RoleEnum.ADMIN,RoleEnum.TEACHER,RoleEnum.STUDENT})
    @PostMapping("/search")
    public ResponseEntity getClazzListByLikeName(int currentPage, int pageSize, String likeName,String flag,Boolean precise){

        int total = clazzService.getClazzListByLikeNameTotal(likeName,flag,precise);
        PageUtils pageUtils = new PageUtils(currentPage,pageSize,total);

        List<ClazzVo> clazzVoList = clazzService.getClazzListByLikeName(pageUtils.getStartIndex(),pageSize,likeName,flag,precise);

        return ResponseUtils.getSuccessResult(new PageListResponse(clazzVoList,currentPage,pageSize,pageUtils.getLastPage(),total));
    }

    @AuthenticationList({RoleEnum.ADMIN,RoleEnum.TEACHER})
    @PostMapping("/update")
    public ResponseEntity updateClazz(@RequestBody Clazz clazz){
        int i = clazzService.updateClazzByEditPage(clazz);

        if (i > 0){
            return ResponseUtils.getSuccessResult();
        }

        return ResponseUtils.getFailResult("操作失败");
    }

    @AuthenticationList({RoleEnum.ADMIN,RoleEnum.TEACHER})
    @PostMapping("/delete")
    public ResponseEntity deleteClazzByCid(@RequestBody Clazz clazz){
        int i = clazzService.deleteClazzByCid(clazz.getCid());

        if (i > 0){
            return ResponseUtils.getSuccessResult();
        }

        return ResponseUtils.getFailResult("操作失败");
    }


}
