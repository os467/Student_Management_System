package pers.os467.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.os467.management.annotion.AuthenticationList;
import pers.os467.management.base.ResponseUtils;
import pers.os467.management.base.response.PageListResponse;
import pers.os467.management.base.response.ResponseEntity;
import pers.os467.management.common.RoleEnum;
import pers.os467.management.entity.Lesson;
import pers.os467.management.service.LessonService;
import pers.os467.management.utils.PageUtils;
import pers.os467.management.vo.GetLessonByLidVo;
import pers.os467.management.vo.LessonVo;

import java.util.List;


@RestController
@RequestMapping("/lesson")
public class LessonController {

    @Autowired
    private LessonService lessonService;

    @AuthenticationList({RoleEnum.ADMIN,RoleEnum.TEACHER})
    @PostMapping("/add")
    public ResponseEntity addLesson(@RequestBody Lesson lesson){

        int i = lessonService.addLesson(lesson);

        if (i > 0){
            return ResponseUtils.getSuccessResult();
        }

        return ResponseUtils.getFailResult("操作失败");
    }

    @AuthenticationList({RoleEnum.ADMIN,RoleEnum.TEACHER,RoleEnum.STUDENT})
    @GetMapping("/getLessonByLid")
    public ResponseEntity getLessonByLid(Integer lid){
        GetLessonByLidVo getLessonByLidVo = lessonService.getLessonByLid(lid);
        if (getLessonByLidVo != null){
            return ResponseUtils.getSuccessResult(getLessonByLidVo);
        }
        return ResponseUtils.getFailResult("未查询到课程信息");
    }

    @AuthenticationList({RoleEnum.ADMIN,RoleEnum.TEACHER,RoleEnum.STUDENT})
    @PostMapping("/search")
    public ResponseEntity getLessonListByLikeName(int currentPage, int pageSize, String likeName,String flag,Boolean precise){

        int total = lessonService.getLessonListByLikeNameTotal(likeName,flag,precise);
        PageUtils pageUtils = new PageUtils(currentPage,pageSize,total);

        List<LessonVo> lessonVoList = lessonService.getLessonListByLikeName(pageUtils.getStartIndex(),pageSize,likeName,flag,precise);

        return ResponseUtils.getSuccessResult(new PageListResponse(lessonVoList,currentPage,pageSize,pageUtils.getLastPage(),total));
    }

    @AuthenticationList({RoleEnum.ADMIN,RoleEnum.TEACHER})
    @PostMapping("/update")
    public ResponseEntity updateLesson(@RequestBody Lesson lesson){

        int i = lessonService.updateLesson(lesson);

        if (i > 0){
            return ResponseUtils.getSuccessResult();
        }

        return ResponseUtils.getFailResult("操作失败");
    }

    @AuthenticationList({RoleEnum.ADMIN,RoleEnum.TEACHER})
    @PostMapping("/delete")
    public ResponseEntity deleteLessonByLid(@RequestBody Lesson lesson){

        ResponseEntity responseEntity = lessonService.deleteLessonByLid(lesson.getLid());

        return responseEntity;
    }

}
