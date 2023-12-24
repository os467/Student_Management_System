package pers.os467.management.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.os467.management.base.ResponseUtils;
import pers.os467.management.base.response.ResponseEntity;
import pers.os467.management.common.Constant;
import pers.os467.management.entity.SK;
import pers.os467.management.mapper.SKMapper;
import pers.os467.management.service.LessonService;
import pers.os467.management.service.SKService;
import pers.os467.management.service.TeacherService;
import pers.os467.management.service.XKService;
import pers.os467.management.vo.*;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SKServiceImpl implements SKService {
    @Autowired
    private SKMapper skMapper;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private LessonService lessonService;

    @Autowired
    private XKService xkService;

    @Override
    public int skCountByTid(Integer tid) {
        return skMapper.skCountByTid(tid);
    }

    @Override
    public int skCountByLid(Integer lid) {
        return skMapper.skCountByLid(lid);
    }


    @Override
    public ResponseEntity deleteSKBySKId(Integer skId) {
        int i = 0;
        if (skId != null){
            int xkCountBySKId = xkService.xkCountBySKId(skId);
            if (xkCountBySKId == 0){
                i = skMapper.deleteSKBySKId(skId);
            }else {
                return ResponseUtils.getFailResult("删除失败，此课程选课数据不为0");
            }
        }
        if (i>0){
            return ResponseUtils.getSuccessResult();
        }else {
            return ResponseUtils.getFailResult("操作失败");
        }
    }

    @Override
    public ResponseEntity addSK(SK sk) {
        SK skByTidAndLid = skMapper.getSKByTidAndLid(sk.getTid(),sk.getLid());
        if (skByTidAndLid != null){
            //存在重复授课项
            return ResponseUtils.getFailResult("存在重复授课项");
        }else {
            //可以添加授课
            int i = skMapper.addSK(sk);
            if (i > 0){
                return ResponseUtils.getSuccessResult();
            }
            return ResponseUtils.getFailResult("操作失败");
        }
    }

    @Override
    public GetSKBySKIdVo getSKBySKId(Integer skId) {
        SK sk = skMapper.getSKBySKId(skId);

        GetSKBySKIdVo getSKBySKIdVo = null;
        if (sk != null){
            GetTeacherByTidVo teacherByTid = teacherService.getTeacherByTid(sk.getTid());
            GetLessonByLidVo lessonByLid = lessonService.getLessonByLid(sk.getLid());

            getSKBySKIdVo = new GetSKBySKIdVo(sk.getSkId(),
                    teacherByTid.getTname(),
                    lessonByLid.getLname(),
                    sk.getTid(),
                    sk.getLid()
            );

        }
        return getSKBySKIdVo;
    }

    @Override
    public int updateSK(SK sk) {
        int i = 0;
        if (sk.getSkId() != null){
            i = skMapper.updateSK(sk);
        }
        return i;
    }

    @Override
    public int getSKListByLikeNameTotal(String likeName, String flag, Boolean precise) {
        String col = Constant.FLAG_MAP.get(flag);
        if (col == null){
            return 0;
        }else {
            return skMapper.getSKListByLikeNameTotal(likeName,col,precise);
        }
    }

    @Override
    public List<SKVo> getSKListByLikeName(int startIndex, int pageSize, String likeName, String flag, Boolean precise) {
        String col = Constant.FLAG_MAP.get(flag);
        if (col == null){
            return null;
        }else {
            List<SK> skListByLikeName = skMapper.getSKListByLikeName(startIndex, pageSize, likeName,col,precise);
            List<SKVo> skVoList = toSKVoList(skListByLikeName);
            return skVoList;
        }
    }


    private List<SKVo> toSKVoList(List<SK> skList) {
        List<SKVo> skVoList = skList.stream().map((sk) -> {
            GetTeacherByTidVo teacherByTid = teacherService.getTeacherByTid(sk.getTid());
            GetLessonByLidVo lessonByLid = lessonService.getLessonByLid(sk.getLid());
            SKVo skVo = new SKVo(sk.getSkId(), teacherByTid.getTname(), lessonByLid.getLname());
            return skVo;
        }).collect(Collectors.toList());
        return skVoList;
    }

}
