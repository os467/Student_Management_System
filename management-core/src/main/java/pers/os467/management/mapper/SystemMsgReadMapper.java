package pers.os467.management.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import pers.os467.management.entity.SystemMsgRead;

import java.util.List;

@Mapper
public interface SystemMsgReadMapper {
    List<SystemMsgRead> getSystemMsgReadList(@Param("uid") Integer uid,
                                             @Param("statusList") List<Integer> statusList);

    int updateSystemMsgReadStatus(@Param("readStatus") Integer readStatus, @Param("uid") Integer uid,@Param("msgReadId") Integer msgReadId);

    int addSystemReads(@Param("systemMsgReads") List<SystemMsgRead> systemMsgReads);
}
