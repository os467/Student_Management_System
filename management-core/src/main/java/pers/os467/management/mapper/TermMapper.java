package pers.os467.management.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import pers.os467.management.entity.Term;

import java.util.Date;
import java.util.List;

@Mapper
public interface TermMapper {
    List<Term> getTermWithinFourYears(@Param("limitDate") Date limitDate);
}
