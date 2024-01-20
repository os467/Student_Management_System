package pers.os467.management.service;

import pers.os467.management.base.response.ResponseEntity;
import pers.os467.management.entity.XK;
import pers.os467.management.utils.JwtUser;
import pers.os467.management.vo.GetXKByXKIdVo;
import pers.os467.management.vo.ScoreVo;
import pers.os467.management.vo.XKVo;

import java.util.List;

public interface XKService {

    /**
     * 添加选课
     * @param xk 选课信息
     * @return 返回实体信息
     */
    ResponseEntity addXK(XK xk);

    /**
     * 通过选课id查询选课记录
     * @param xkId 选课id
     * @return 选课记录
     */
    GetXKByXKIdVo getXKByXKId(Integer xkId);

    /**
     * 获取到查询的选课条目总计
     * 默认为模糊查询
     * 如果是学生账户则只能按照用户名所对应的学号查询到自己的条目信息
     * @param likeName 查询关键字
     * @param flag 查询的字段标记，提供标记集合获取到对应数据库字段名
     * @param precise 是否开启精确查询
     * @param jwtUser 登录用户
     * @return 返回查询到的记录条数
     */
    int getXKListByLikeNameTotal(String likeName, String flag, Boolean precise, JwtUser jwtUser);

    /**
     * 获取到查询的选课条目分页结果
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
    List<XKVo> getXKListByLikeName(int startIndex, int pageSize, String likeName, String flag, Boolean precise, JwtUser jwtUser);

    /**
     * 更新选课信息
     * @param xk 提供更新信息
     * @return 影响记录条数
     */
    int updateXK(XK xk);

    /**
     * 通过选课号删除选课记录
     * @param xkId 选课号
     * @return 返回影响记录条数
     */
    int deleteXKByXKId(Integer xkId);

    /**
     * 通过授课id查询符合条件的选课记录
     * @param skId 授课id
     * @return 符合条件的记录数
     */
    Integer xkCountBySKId(Integer skId);

    /**
     * 通过学生id删除选课记录
     * @param sid 学生id
     */
    void deleteXKWithSid(Integer sid);

    /**
     * 查询分数
     * @param likeName 查询关键字
     * @param flag 查询字段标识
     * @param term 学期
     * @param filterFlag 过滤条件
     * @return 查询结果
     */
    ScoreVo getScoreResult(String likeName, String flag, String term, String filterFlag);

    /**
     * 指定班级查询分数
     * @param likeName 查询关键字
     * @param skId 要查询的授课id
     * @param term 学期
     * @param filterFlag 过滤条件
     * @return 查询结果
     */
    ScoreVo getScoreByClazzResult(String likeName, String skId, String term, String filterFlag);
}
