package pers.os467.management.service;

import pers.os467.management.base.response.ResponseEntity;
import pers.os467.management.entity.Student;
import pers.os467.management.utils.JwtUser;
import pers.os467.management.vo.GetStudentBySidVo;
import pers.os467.management.vo.StudentVo;

import java.util.List;

public interface StudentService {

    /**
     * 通过学生号删除学生
     * 需要删除学生相关的选课记录
     * 需要删除学生关联的用户账号
     * @param sid 学生号
     * @return 返回影响记录条数
     */
    int deleteStudentBySid(Integer sid);

    /**
     * 添加学生数据
     * 在添加数据的同时按需创建学生类型账户
     * 学生账户生成方式：
     * 账户名为 100 + 班级号 + 班内最大学号加一 (如果是1号班级1号学生则为 100101)
     * 班级内账户最大学号不得超过99，否则不允许添加
     * 所在班级人数+1
     * @param student 添加的新数据(不包含学号，数据库自动递增)
     * @param createUser 是否创建用户
     * @return 返回操作结果
     */
    ResponseEntity addStudent(Student student, Boolean createUser);

    /**
     * 通过学号获取到学生信息
     * @param sid 学号
     * @return 返回学生信息
     */
    GetStudentBySidVo getStudentBySid(Integer sid);

    /**
     * 更新学生的信息
     * 注意：更新学生班级信息时，需要在原来的班级人数上减一，在新的班级人数上加一
     * @param student 提供更新信息
     * @return 影响记录条数
     */
    int updateStudent(Student student);

    /**
     * 获取到查询的学生条目总计
     * 默认为模糊查询
     * 如果是学生账户则只能按照用户名所对应的学号查询到自己的条目信息
     * @param likeName 查询关键字
     * @param flag 查询的字段标记，提供标记集合获取到对应数据库字段名
     * @param precise 是否开启精确查询
     * @param jwtUser 登录用户
     * @return 返回查询到的记录条数
     */
    int getStudentListByLikeNameTotal(String likeName, String flag, Boolean precise,
                                      JwtUser jwtUser);

    /**
     * 获取到查询的学生条目分页结果
     * 默认为模糊查询
     * 如果是学生账户则只能按照用户名所对应的学号查询到自己的条目信息
     * @param startIndex 起始索引
     * @param pageSize 分页单位
     * @param likeName 查询关键字
     * @param flag 查询的字段标记，提供标记集合获取到对应数据库字段名
     * @param precise 是否开启精确查询
     * @param jwtUser 登录用户
     * @return 返回查询到的列表
     */
    List<StudentVo> getStudentListByLikeName(Integer startIndex, Integer pageSize,
                                             String likeName,
                                             String flag,
                                             Boolean precise,
                                             JwtUser jwtUser);
}
