package pers.os467.management.service;

import pers.os467.management.base.response.ResponseEntity;
import pers.os467.management.entity.SK;
import pers.os467.management.vo.GetSKBySKIdVo;
import pers.os467.management.vo.SKVo;

import java.util.List;

public interface SKService {
    /**
     * 根据授课信息添加授课记录
     * @param sk 授课信息
     * @return 返回响应实体
     */
    ResponseEntity addSK(SK sk);

    /**
     * 根据授课id查询到授课记录
     * @param skId 授课id
     * @return 授课记录
     */
    GetSKBySKIdVo getSKBySKId(Integer skId);

    /**
     * 获取到模糊查询的班级总条目数
     * @param likeName 模糊查询条件
     * @param flag 查询字段标识
     * @param precise 是否精确查询
     * @return 影响记录条数
     */
    int getSKListByLikeNameTotal(String likeName, String flag, Boolean precise);

    /**
     * 通过模糊查询查询到的授课列表分页结果
     * @param startIndex 分页起始索引
     * @param pageSize 分页大小
     * @param likeName 模糊查询条件
     * @param flag 查询字段标识
     * @param precise 是否精确查询
     * @return 查询到的结果列表
     */
    List<SKVo> getSKListByLikeName(int startIndex, int pageSize, String likeName, String flag, Boolean precise);

    /**
     * 通过课程信息更新授课数据
     * @param sk 授课信息
     * @return 影响记录条数
     */
    int updateSK(SK sk);

    /**
     * 删除授课记录
     * 需要查询是否有对应的选课记录
     * @param skId
     * @return 返回响应实体
     */
    ResponseEntity deleteSKBySKId(Integer skId);

    /**
     * 通过教师号筛选查询到授课数量
     * @param tid 教师号
     * @return 符合条件的授课数量
     */
    int skCountByTid(Integer tid);

    /**
     * 通过课程号筛选查询到授课数量
     * @param lid 课程号
     * @return 符合条件的授课数量
     */
    int skCountByLid(Integer lid);
}
