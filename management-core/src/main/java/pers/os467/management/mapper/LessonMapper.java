package pers.os467.management.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import pers.os467.management.entity.Lesson;

import java.util.List;

@Mapper
public interface LessonMapper {
    int addLesson(@Param("lesson") Lesson lesson);

    Lesson getLessonByLid(@Param("lid")Integer lid);

    int updateLesson(@Param("lesson")Lesson lesson);

    int getLessonListByLikeNameTotal(@Param("likeName") String likeName,
                                     @Param("col") String col,
                                     @Param("precise") Boolean precise);

    List<Lesson> getLessonListByLikeName(@Param("startIndex") int startIndex,
                                         @Param("pageSize") int pageSize,
                                         @Param("likeName") String likeName,
                                         @Param("col") String col,
                                         @Param("precise")Boolean precise);



    int deleteLessonByLid(@Param("lid")Integer lid);
}
