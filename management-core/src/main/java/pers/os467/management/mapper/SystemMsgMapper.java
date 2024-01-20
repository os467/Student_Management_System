package pers.os467.management.mapper;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import pers.os467.management.entity.SystemMsg;


import java.util.List;
import java.util.Map;

@Mapper
public interface SystemMsgMapper {
    @MapKey("id")
    Map<Integer,SystemMsg> getSystemMsgMap(@Param("idList") List<Integer> idList);

    int addSystemMsg(@Param("systemMsg") SystemMsg systemMsg);
}
