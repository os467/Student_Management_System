package pers.os467.management.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import pers.os467.management.entity.SK;

import java.util.List;

@Mapper
public interface SKMapper {
    int addSK(@Param("sk") SK sk);

    SK getSKBySKId(@Param("skId")Integer skId);

    int updateSK(@Param("sk")SK sk);

    int getSKListByLikeNameTotal(@Param("likeName") String likeName, @Param("col") String col,
                                 @Param("precise") Boolean precise);

    List<SK> getSKListByLikeName(@Param("startIndex") int startIndex,
                                 @Param("pageSize") int pageSize,
                                 @Param("likeName") String likeName,
                                 @Param("col") String col,
                                 @Param("precise")Boolean precise);

    List<SK> getSKList(@Param("startIndex")int startIndex,
                                 @Param("pageSize")int pageSize);

    int getSKTotal();

    int deleteSKBySKId(@Param("skId")Integer skId);

    int skCountByTid(@Param("tid") Integer tid);

    int skCountByLid(@Param("lid") Integer lid);

    SK getSKByTidAndLid(@Param("tid") Integer tid,@Param("lid") Integer lid);
}
