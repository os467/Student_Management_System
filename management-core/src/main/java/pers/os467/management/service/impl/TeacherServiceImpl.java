package pers.os467.management.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.os467.management.base.ResponseUtils;
import pers.os467.management.base.response.ResponseEntity;
import pers.os467.management.common.Constant;
import pers.os467.management.entity.Teacher;
import pers.os467.management.mapper.TeacherMapper;
import pers.os467.management.service.SKService;
import pers.os467.management.service.TeacherService;
import pers.os467.management.vo.GetTeacherByTidVo;
import pers.os467.management.vo.TeacherVo;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private SKService skService;

    @Override
    public ResponseEntity deleteTeacherByTid(Integer tid) {
        int i = 0;
        if (tid != null){
            Integer skCountByTid = 0;
            skCountByTid = skService.skCountByTid(tid);
            if (skCountByTid == 0){
                //记录条数为0可以删除
                i = teacherMapper.deleteTeacherByTid(tid);
            }else {
                return ResponseUtils.getFailResult("删除失败，教师授课数量不为0");
            }
        }
        if (i>0){
            return ResponseUtils.getSuccessResult();
        }else {
            return ResponseUtils.getFailResult("操作失败");
        }
    }

    @Override
    public int addTeacher(Teacher teacher) {
        return teacherMapper.addTeacher(teacher);
    }

    @Override
    public GetTeacherByTidVo getTeacherByTid(Integer tid) {
        Teacher teacher = teacherMapper.getTeacherByTid(tid);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        GetTeacherByTidVo getTeacherByTidVo = new GetTeacherByTidVo(teacher.getTid(),
                teacher.getTname(),
                teacher.getSex(),
                simpleDateFormat.format(teacher.getBirthday())
        );
        return getTeacherByTidVo;
    }

    @Override
    public int updateTeacher(Teacher teacher) {
        int i = 0;
        if (teacher.getTid() != null){
            i = teacherMapper.updateTeacher(teacher);
        }
        return i;
    }

    @Override
    public int getTeacherListByLikeNameTotal(String likeName, String flag, Boolean precise) {
        String col = Constant.FLAG_MAP.get(flag);
        if (col == null){
            return 0;
        }else {
            return teacherMapper.getTeacherListByLikeNameTotal(likeName,col,precise);
        }
    }

    @Override
    public List<TeacherVo> getTeacherListByLikeName(int startIndex, int pageSize, String likeName, String flag, Boolean precise) {
        String col = Constant.FLAG_MAP.get(flag);
        if (col == null){
            return null;
        }else {
            List<Teacher> teacherListByLikeName = teacherMapper.getTeacherListByLikeName(startIndex, pageSize, likeName,col,precise);
            List<TeacherVo> teacherVoList = toTeacherVoList(teacherListByLikeName);
            return teacherVoList;
        }
    }


    private List<TeacherVo> toTeacherVoList(List<Teacher> teacherList) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        List<TeacherVo> teacherVoList = teacherList.stream().map((teacher) -> {
            TeacherVo teacherVo = new TeacherVo(teacher.getTid(),
                    teacher.getTname(),
                    teacher.getSex().equals(1) ? "男" : "女",
                    simpleDateFormat.format(teacher.getBirthday())
            );
            return teacherVo;
        }).collect(Collectors.toList());
        return teacherVoList;
    }
}
