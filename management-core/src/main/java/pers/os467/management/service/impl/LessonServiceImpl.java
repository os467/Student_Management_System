package pers.os467.management.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.os467.management.base.ResponseUtils;
import pers.os467.management.base.response.ResponseEntity;
import pers.os467.management.common.Constant;
import pers.os467.management.entity.Lesson;
import pers.os467.management.mapper.LessonMapper;
import pers.os467.management.service.LessonService;
import pers.os467.management.service.SKService;
import pers.os467.management.vo.GetLessonByLidVo;
import pers.os467.management.vo.LessonVo;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LessonServiceImpl implements LessonService {
    @Autowired
    private LessonMapper lessonMapper;

    @Autowired
    private SKService skService;

    @Override
    public ResponseEntity deleteLessonByLid(Integer lid) {
        int i = 0;
        if (lid != null){
            int skCountByLid = skService.skCountByLid(lid);
            if (skCountByLid == 0){
                i = lessonMapper.deleteLessonByLid(lid);
            }else {
                return ResponseUtils.getFailResult("删除失败，授课记录不为0");
            }
        }
        if (i > 0){
            return ResponseUtils.getSuccessResult();
        }else {
            return ResponseUtils.getFailResult("操作失败");
        }
    }

    @Override
    public int addLesson(Lesson lesson) {
        return lessonMapper.addLesson(lesson);
    }

    @Override
    public GetLessonByLidVo getLessonByLid(Integer lid) {
        Lesson lesson = lessonMapper.getLessonByLid(lid);
        GetLessonByLidVo getLessonByLidVo = new GetLessonByLidVo(lesson.getLid(),
                lesson.getLname(),
                lesson.getHours()
        );
        return getLessonByLidVo;
    }

    @Override
    public int updateLesson(Lesson lesson) {
        int i = 0;
        if (lesson.getLid() != null){
            i = lessonMapper.updateLesson(lesson);
        }
        return i;
    }

    @Override
    public int getLessonListByLikeNameTotal(String likeName, String flag, Boolean precise) {
        String col = Constant.FLAG_MAP.get(flag);
        if (col == null){
          return 0;
        }else {
            return lessonMapper.getLessonListByLikeNameTotal(likeName,col,precise);
        }
    }

    @Override
    public List<LessonVo> getLessonListByLikeName(int startIndex, int pageSize, String likeName, String flag, Boolean precise) {
        String col = Constant.FLAG_MAP.get(flag);
        if (col == null){
            return null;
        }else {
            List<Lesson> lessonListByLikeName = lessonMapper.getLessonListByLikeName(startIndex, pageSize, likeName,col,precise);
            List<LessonVo> lessonVoList = toLessonVoList(lessonListByLikeName);
            return lessonVoList;
        }
    }


    private List<LessonVo> toLessonVoList(List<Lesson> lessonList) {

        List<LessonVo> lessonVoList = lessonList.stream().map((lesson) -> {
            LessonVo lessonVo = new LessonVo(lesson.getLid(),
                    lesson.getLname(),
                    lesson.getHours()
            );
            return lessonVo;
        }).collect(Collectors.toList());
        return lessonVoList;
    }
}
