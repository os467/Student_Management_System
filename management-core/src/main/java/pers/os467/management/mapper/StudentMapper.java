package pers.os467.management.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import pers.os467.management.entity.Student;

import java.util.List;

@Mapper
public interface StudentMapper {

    List<Student> getStudentList(@Param("startIndex") int startIndex,
                                 @Param("pageSize") int pageSize);

    int getStudentTotal();

    int deleteStudentBySid(@Param("sid") Integer sid);

    int addStudent(@Param("student") Student student);

    Student getStudentBySid(@Param("sid") Integer sid);

    int updateStudent(@Param("student") Student student);

    int getStudentListByLikeNameTotal(@Param("likeName") String likeName, @Param("col") String col,@Param("precise") Boolean precise);

    List<Student> getStudentListByLikeName(@Param("startIndex")Integer startIndex,
                                             @Param("pageSize")Integer pageSize,
                                             @Param("likeName") String likeName,
                                           @Param("col") String col,
                                           @Param("precise") Boolean precise);

    Integer getStudentHighestSidByCid(@Param("cid") Integer cid);
}
