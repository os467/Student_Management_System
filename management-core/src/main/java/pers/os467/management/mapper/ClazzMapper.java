package pers.os467.management.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import pers.os467.management.entity.Clazz;

import java.util.List;

@Mapper
public interface ClazzMapper {
    String getClazzNameByCid(@Param("cid") Integer cid);

    List<Clazz> getClazzList();

    int addClazz(@Param("clazz") Clazz clazz);

    Clazz getClazzByCid(@Param("cid")Integer cid);

    int updateClazz(@Param("clazz")Clazz clazz);

    int getClazzListByLikeNameTotal(@Param("likeName") String likeName,
                                    @Param("col") String col,
                                    @Param("precise") Boolean precise);

    List<Clazz> getClazzListByLikeName(@Param("startIndex") int startIndex,
                                       @Param("pageSize") int pageSize,
                                       @Param("likeName") String likeName,
                                       @Param("col") String col,
                                       @Param("precise")Boolean precise);

    List<Clazz> getClazzPageList(@Param("startIndex")int startIndex,
                                 @Param("pageSize")int pageSize);

    int getClazzTotal();

    int deleteClazzByCid(@Param("cid")Integer cid);

}
