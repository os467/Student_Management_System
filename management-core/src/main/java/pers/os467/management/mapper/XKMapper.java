package pers.os467.management.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import pers.os467.management.entity.XK;

import java.util.List;

@Mapper
public interface XKMapper {
    int addXK(@Param("xk") XK xk);

    XK getXKByXKId(@Param("xkId")Integer xkId);

    int updateXK(@Param("xk")XK xk);

    int getXKListByLikeNameTotal(@Param("likeName") String likeName,
                                 @Param("precise")Boolean precise, @Param("col") String col);

    List<XK> getXKListByLikeName(@Param("startIndex") int startIndex,
                                 @Param("pageSize") int pageSize,
                                 @Param("likeName") String likeName,
                                 @Param("col") String col,
                                 @Param("precise")Boolean precise);

    int deleteXKByXKId(@Param("xkId")Integer xkId);

    Integer xkCountBySKId(@Param("skId") Integer skId);

    void deleteXKWithSid(@Param("sid") Integer sid);

    List<XK> getXKListByLikeNameWithSid(@Param("startIndex") int startIndex,
                                        @Param("pageSize") int pageSize,
                                        @Param("likeName") String likeName,
                                        @Param("col") String col,
                                        @Param("precise")Boolean precise,
                                        @Param("sid") String sid);

    int getXKListByLikeNameTotalWithSid(@Param("likeName") String likeName,
                                        @Param("col") String col,
                                        @Param("precise") Boolean precise,
                                        @Param("sid") String sid);

    String averageScoreFilterByCol(@Param("likeName") String likeName,
                                   @Param("col")String col,
                                   @Param("term")String term);

    XK highestScoreFilterByCol(@Param("likeName")String likeName,
                                     @Param("col")String col,
                                     @Param("term")String term);

    XK lowestScoreFilterByCol(@Param("likeName")String likeName,
                                    @Param("col")String col,
                                    @Param("term")String term);


    String averageScoreByClazzFilterByCol(@Param("likeName")String likeName,
                                          @Param("skId")String skId,
                                          @Param("term")String term);

    XK highestScoreByClazzFilterByCol(@Param("likeName")String likeName,
                                      @Param("skId")String skId,
                                      @Param("term")String term);

    XK lowestScoreByClazzFilterByCol(@Param("likeName")String likeName,
                                     @Param("skId")String skId,
                                     @Param("term")String term);

    String passRate(@Param("likeName")String likeName,@Param("col") String col,@Param("term") String term);

    String passRateByClazz(@Param("likeName")String likeName,@Param("skId") String skId,@Param("term") String term);

    XK getXKBySidAndSKId(@Param("sid") Integer sid,@Param("skId") Integer skId);
}
