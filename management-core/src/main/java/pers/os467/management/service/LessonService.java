package pers.os467.management.service;

import pers.os467.management.base.response.ResponseEntity;
import pers.os467.management.entity.Lesson;
import pers.os467.management.vo.GetLessonByLidVo;
import pers.os467.management.vo.LessonVo;

import java.util.List;

public interface LessonService {
    /**
     * 添加课程
     * @param lesson 课程信息
     * @return 影响记录条数
     */
    int addLesson(Lesson lesson);

    /**
     * 通过课程id查询课程信息
     * @param lid 课程id
     * @return 课程信息
     */
    GetLessonByLidVo getLessonByLid(Integer lid);

    /**
     * 获取到模糊查询的班级总条目数
     * @param likeName 模糊查询条件
     * @param flag 查询字段标识
     * @param precise 是否精确查询
     * @return 影响记录条数
     */
    int getLessonListByLikeNameTotal(String likeName, String flag, Boolean precise);

    /**
     * 通过模糊查询查询到的课程列表分页结果
     * @param startIndex 分页起始索引
     * @param pageSize 分页大小
     * @param likeName 模糊查询条件
     * @param flag 查询字段标识
     * @param precise 是否精确查询
     * @return 查询到的结果列表
     */
    List<LessonVo> getLessonListByLikeName(int startIndex, int pageSize, String likeName, String flag, Boolean precise);

    /**
     * 通过课程信息更新课程数据
     * @param lesson 课程信息
     * @return 影响记录条数
     */
    int updateLesson(Lesson lesson);

    /**
     * 删除课程
     * 需要检查是否有关联的授课记录
     * 若存在授课记录，则无法删除
     * @param lid
     * @return
     */
    ResponseEntity deleteLessonByLid(Integer lid);

}
