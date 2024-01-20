package pers.os467.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.os467.management.annotion.AuthenticationList;
import pers.os467.management.base.ResponseUtils;
import pers.os467.management.base.response.PageListResponse;
import pers.os467.management.base.response.ResponseEntity;
import pers.os467.management.common.Constant;
import pers.os467.management.common.RoleEnum;
import pers.os467.management.entity.Student;
import pers.os467.management.service.StudentService;
import pers.os467.management.utils.JwtUser;
import pers.os467.management.utils.PageUtils;
import pers.os467.management.vo.GetStudentBySidVo;
import pers.os467.management.vo.StudentVo;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private HttpSession session;

    @Autowired
    private StudentService studentService;

    @AuthenticationList({RoleEnum.ADMIN,RoleEnum.TEACHER})
    @PostMapping("/add")
    public ResponseEntity addStudent(@RequestBody Student student,Boolean createUser){
        //学生表中插入一条数据
        return studentService.addStudent(student,createUser);
    }

    @AuthenticationList({RoleEnum.ADMIN,RoleEnum.TEACHER,RoleEnum.STUDENT})
    @GetMapping("/getStudentBySid")
    public ResponseEntity getStudentBySid(Integer sid){
        GetStudentBySidVo getStudentBySidVo = studentService.getStudentBySid(sid);
        if (getStudentBySidVo != null){
            return ResponseUtils.getSuccessResult(getStudentBySidVo);
        }
        return ResponseUtils.getFailResult("未查询到学生信息");
    }

    @AuthenticationList({RoleEnum.ADMIN,RoleEnum.TEACHER,RoleEnum.STUDENT})
    @PostMapping("/search")
    public ResponseEntity getStudentListByLikeName(int currentPage, int pageSize, String likeName,String flag,Boolean precise){

        JwtUser jwtUser = (JwtUser) session.getAttribute(Constant.JWT_USER);

        int total = studentService.getStudentListByLikeNameTotal(
                likeName,flag,precise,
                jwtUser);
        PageUtils pageUtils = new PageUtils(currentPage,pageSize,total);

        List<StudentVo> studentList = studentService.getStudentListByLikeName(
                pageUtils.getStartIndex(),pageSize,
                likeName, flag, precise,
                jwtUser);

        return ResponseUtils.getSuccessResult(new PageListResponse(studentList,currentPage,pageSize,pageUtils.getLastPage(),total));
    }

    @AuthenticationList({RoleEnum.ADMIN,RoleEnum.TEACHER})
    @PostMapping("/update")
    public ResponseEntity updateStudent(@RequestBody Student student){

        //更新学生表学生数据
        int i = studentService.updateStudent(student);

        if (i > 0){
            return ResponseUtils.getSuccessResult();
        }

        return ResponseUtils.getFailResult("操作失败");
    }

    @AuthenticationList({RoleEnum.ADMIN})
    @PostMapping("/delete")
    public ResponseEntity deleteStudentBySid(@RequestBody Student student){

        int i = studentService.deleteStudentBySid(student.getSid());

        if (i > 0){
            return ResponseUtils.getSuccessResult();
        }

        return ResponseUtils.getFailResult("操作失败");
    }


}
