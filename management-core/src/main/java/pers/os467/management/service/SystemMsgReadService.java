package pers.os467.management.service;


import pers.os467.management.base.response.ResponseEntity;
import pers.os467.management.entity.SystemMsgRead;
import pers.os467.management.form.SystemMsgForm;
import pers.os467.management.utils.JwtUser;

import java.util.List;

public interface SystemMsgReadService {

    /**
     * 获取到系统消息读取记录列表
     * @param uid 接收消息的用户号
     * @param statusList 符合条件的状态列表
     * @return 系统消息读取列表
     */
    List<SystemMsgRead> getSystemMsgReadList(Integer uid, List<Integer> statusList);

    /**
     * 用户读取系统消息，更新系统消息读取记录状态
     * @param msgReadId 被读取的系统消息id
     * @param jwtUser 登录用户
     * @return 返回响应实体
     */
    ResponseEntity readSystemMsg(Integer msgReadId,JwtUser jwtUser);

    /**
     * 用户删除系统消息，更新系统消息读取记录状态
     * @param msgReadId 读取记录id
     * @param jwtUser 登录用户
     * @return 返回响应实体
     */
    ResponseEntity deleteSystemMsg(Integer msgReadId, JwtUser jwtUser);

    /**
     * 用户添加系统消息，需要保存系统消息，并且添加符合推送权限组用户的消息记录
     * @param systemMsgForm 系统消息表单
     * @param jwtUser 登录用户
     * @return 返回响应实体
     */
    ResponseEntity addSystemMsg(SystemMsgForm systemMsgForm, JwtUser jwtUser);
}
