package pers.os467.management.service;

import pers.os467.management.base.response.ResponseEntity;
import pers.os467.management.entity.Teacher;
import pers.os467.management.vo.GetTeacherByTidVo;
import pers.os467.management.vo.TeacherVo;

import java.util.List;

public interface TeacherService {

    /**
     * 添加教师数据
     * @param teacher 添加的数据
     * @return 返回影响记录条数
     */
    int addTeacher(Teacher teacher);

    /**
     * 通过教师号获取到教师信息
     * @param tid 教师号
     * @return 返回教师信息
     */
    GetTeacherByTidVo getTeacherByTid(Integer tid);

    /**
     * 获取到模糊查询的教师条目总计
     * @param likeName 匹配值
     * @param flag 查询的字段标记，提供标记集合获取到对应数据库字段名
     * @param precise
     * @return 返回查询结果条数
     */
    int getTeacherListByLikeNameTotal(String likeName, String flag, Boolean precise);

    /**
     * 通过模糊查询获取到教师列表的分页结果
     * @param startIndex 当前分页起始索引
     * @param pageSize 分页单位
     * @param likeName 匹配值
     * @param flag 查询的字段标记，提供标记集合获取到对应数据库字段名
     * @param precise
     * @return 返回查询结果
     */
    List<TeacherVo> getTeacherListByLikeName(int startIndex, int pageSize, String likeName, String flag, Boolean precise);

    /**
     * 更新教师
     * @param teacher 更新的数据
     * @return 返回影响记录条数
     */
    int updateTeacher(Teacher teacher);

    /**
     * 通过教师号删除教师信息
     * 删除教师逻辑：
     * 教师需要没有授课记录，才能进行删除
     * @param tid 教师号
     * @return 返回操作结果
     */
    ResponseEntity deleteTeacherByTid(Integer tid);

}
