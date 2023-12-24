package pers.os467.management.service;

import pers.os467.management.entity.Clazz;
import pers.os467.management.vo.ClazzVo;

import java.util.List;

public interface ClazzService {

    /**
     * 通过班级号获取到班级名称
     * @param cid 班级号
     * @return 班级名称
     */
    String getClazzNameByCid(Integer cid);

    /**
     * 获取到所有班级列表
     * @return 班级列表
     */
    List<Clazz> getClazzList();

    /**
     * 添加班级数据
     * @param clazz 添加的数据
     * @return 影响记录条数
     */
    int addClazz(Clazz clazz);

    /**
     * 通过班级号获取到班级信息
     * @param cid 班级号
     * @return 查询结果
     */
    Clazz getClazzByCid(Integer cid);

    /**
     * 获取到模糊查询的班级总条目数
     * @param likeName 模糊查询条件
     * @param flag 查询字段标识
     * @param precise 是否精确查询
     * @return 影响记录条数
     */
    int getClazzListByLikeNameTotal(String likeName, String flag, Boolean precise);

    /**
     * 通过模糊查询查询到的班级列表分页结果
     * @param startIndex 分页起始索引
     * @param pageSize 分页大小
     * @param likeName 模糊查询条件
     * @param flag 查询字段标识
     * @param precise 是否精确查询
     * @return 查询到的结果列表
     */
    List<ClazzVo> getClazzListByLikeName(int startIndex, int pageSize, String likeName, String flag, Boolean precise);

    /**
     * 更新班级数据
     * @param clazz 更新数据
     * @return 影响记录条数
     */
    int updateClazz(Clazz clazz);

    /**
     * 通过班级号删除班级信息
     * @param cid 需要删除的班级id
     * @return 影响记录条数
     */
    int deleteClazzByCid(Integer cid);

    /**
     * 获取到班级总条目数
     * @return 记录条数
     */
    int getClazzTotal();

    /**
     * 通过分页获取到班级列表
     * @param startIndex 当前分页下的起始索引
     * @param pageSize 分页单位
     * @return 班级列表
     */
    List<ClazzVo> getClazzPageList(int startIndex, int pageSize);

    /**
     * 通过班级号更改班级成员数量
     * @param i 更新附加值(负数代表减少，正数代表增加)
     * @param cid 班级号
     * @return 影响记录条数
     */
    int addClazzMemberNumByCid(int i, Integer cid);

    /**
     * 通过编辑页面更新班级数据
     * @param clazz 更新的数据
     * @return 影响记录条数
     */
    int updateClazzByEditPage(Clazz clazz);

}
