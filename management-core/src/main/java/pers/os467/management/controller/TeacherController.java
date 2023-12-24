package pers.os467.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.os467.management.annotion.AuthenticationList;
import pers.os467.management.base.ResponseUtils;
import pers.os467.management.base.response.PageListResponse;
import pers.os467.management.base.response.ResponseEntity;
import pers.os467.management.common.RoleEnum;
import pers.os467.management.entity.Teacher;
import pers.os467.management.service.TeacherService;
import pers.os467.management.utils.PageUtils;
import pers.os467.management.vo.GetTeacherByTidVo;
import pers.os467.management.vo.TeacherVo;

import java.util.List;


@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @AuthenticationList({RoleEnum.ADMIN})
    @PostMapping("/add")
    public ResponseEntity addTeacher(@RequestBody Teacher teacher){

        int i = teacherService.addTeacher(teacher);

        if (i > 0){
            return ResponseUtils.getSuccessResult();
        }

        return ResponseUtils.getFailResult("操作失败");
    }

    @AuthenticationList({RoleEnum.ADMIN})
    @GetMapping("/getTeacherByTid")
    public ResponseEntity getTeacherByTid(Integer tid){
        GetTeacherByTidVo getTeacherByTidVo = teacherService.getTeacherByTid(tid);
        if (getTeacherByTidVo != null){
            return ResponseUtils.getSuccessResult(getTeacherByTidVo);
        }
        return ResponseUtils.getFailResult("未查询到教师信息");
    }

    @AuthenticationList({RoleEnum.ADMIN,RoleEnum.TEACHER})
    @PostMapping("/search")
    public ResponseEntity getTeacherListByLikeName(int currentPage, int pageSize, String likeName,String flag,Boolean precise){

        int total = teacherService.getTeacherListByLikeNameTotal(likeName,flag,precise);
        PageUtils pageUtils = new PageUtils(currentPage,pageSize,total);

        List<TeacherVo> teacherVoList = teacherService.getTeacherListByLikeName(pageUtils.getStartIndex(),pageSize,likeName,flag,precise);

        return ResponseUtils.getSuccessResult(new PageListResponse(teacherVoList,currentPage,pageSize,pageUtils.getLastPage(),total));
    }

    @AuthenticationList({RoleEnum.ADMIN})
    @PostMapping("/update")
    public ResponseEntity updateTeacher(@RequestBody Teacher teacher){

        int i = teacherService.updateTeacher(teacher);

        if (i > 0){
            return ResponseUtils.getSuccessResult();
        }

        return ResponseUtils.getFailResult("操作失败");
    }

    @AuthenticationList({RoleEnum.ADMIN})
    @PostMapping("/delete")
    public ResponseEntity deleteTeacherByTid(@RequestBody Teacher teacher){

        ResponseEntity responseEntity = teacherService.deleteTeacherByTid(teacher.getTid());

        return responseEntity;
    }

}
