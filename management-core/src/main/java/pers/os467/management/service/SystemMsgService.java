package pers.os467.management.service;

import pers.os467.management.base.response.ResponseEntity;
import pers.os467.management.entity.SystemMsg;
import pers.os467.management.utils.JwtUser;

public interface SystemMsgService {
    /**
     * 获取到系统消息列表
     * @param jwtUser 登录用户
     * @return 响应实体
     */
    ResponseEntity getSystemMsgList(JwtUser jwtUser);

    /**
     * 根据提供的系统消息信息添加系统消息
     * @param systemMsg 系统消息信息
     * @return 影响记录条数
     */
    int addSystemMsg(SystemMsg systemMsg);
}
