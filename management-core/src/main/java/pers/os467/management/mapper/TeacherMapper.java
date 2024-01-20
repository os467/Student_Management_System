package pers.os467.management.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import pers.os467.management.entity.Teacher;

import java.util.List;

@Mapper
public interface TeacherMapper {
    int addTeacher(@Param("teacher") Teacher teacher);

    Teacher getTeacherByTid(@Param("tid")Integer tid);

    int updateTeacher(@Param("teacher")Teacher teacher);

    int getTeacherListByLikeNameTotal(@Param("likeName") String likeName,
                                      @Param("col") String col,
                                      @Param("precise") Boolean precise);

    List<Teacher> getTeacherListByLikeName(@Param("startIndex") int startIndex,
                                           @Param("pageSize") int pageSize,
                                           @Param("likeName") String likeName,
                                           @Param("col") String col,
                                           @Param("precise")Boolean precise);


    int deleteTeacherByTid(@Param("tid")Integer tid);
}
