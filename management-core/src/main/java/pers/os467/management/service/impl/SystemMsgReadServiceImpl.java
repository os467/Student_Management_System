package pers.os467.management.service.impl;

import org.apache.ibatis.reflection.ArrayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.os467.management.base.ResponseUtils;
import pers.os467.management.base.response.ResponseEntity;
import pers.os467.management.common.Constant;
import pers.os467.management.entity.SystemMsg;
import pers.os467.management.entity.SystemMsgRead;
import pers.os467.management.entity.User;
import pers.os467.management.form.SystemMsgForm;
import pers.os467.management.mapper.SystemMsgMapper;
import pers.os467.management.mapper.SystemMsgReadMapper;
import pers.os467.management.service.SystemMsgReadService;
import pers.os467.management.service.SystemMsgService;
import pers.os467.management.service.UserService;
import pers.os467.management.utils.JwtUser;
import pers.os467.management.utils.SqlUtils;


import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SystemMsgReadServiceImpl implements SystemMsgReadService {

    @Autowired
    private SystemMsgReadMapper systemMsgReadMapper;

    @Autowired
    private SystemMsgService systemMsgService;

    @Autowired
    private UserService userService;

    @Override
    public List<SystemMsgRead> getSystemMsgReadList(Integer uid, List<Integer> statusList) {
        return systemMsgReadMapper.getSystemMsgReadList(uid,statusList);
    }

    @Override
    public ResponseEntity readSystemMsg(Integer msgReadId,JwtUser jwtUser) {
        if (jwtUser.getUid() != null){
            int i = systemMsgReadMapper.updateSystemMsgReadStatus(Constant.SYSTEM_MSG_READ_STATUS_READ, jwtUser.getUid(), msgReadId);
            if (i > 0){
                return ResponseUtils.getSuccessResult();
            }
            return ResponseUtils.getFailResult("操作失败");
        }
        return ResponseUtils.getFailResult("请检查token是否有效");
    }

    @Override
    public ResponseEntity deleteSystemMsg(Integer msgReadId, JwtUser jwtUser) {
        if (jwtUser.getUid() != null){
            int i = systemMsgReadMapper.updateSystemMsgReadStatus(Constant.SYSTEM_MSG_READ_STATUS_DELETE, jwtUser.getUid(), msgReadId);
            if (i > 0){
                return ResponseUtils.getSuccessResult();
            }
            return ResponseUtils.getFailResult("操作失败");
        }
        return ResponseUtils.getFailResult("请检查token是否有效");
    }

    @Override
    public ResponseEntity addSystemMsg(SystemMsgForm systemMsgForm, JwtUser jwtUser) {
        if (jwtUser.getUid() != null){
            System.out.println(systemMsgForm);
            List<Integer> receivedRids = systemMsgForm.getReceivedRids();
            //整理推送目标角色
            receivedRids.sort(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o1 - o2;
                }
            });
            systemMsgForm.setReceivedRids(receivedRids);

            //要推送的系统消息对象
            SystemMsg systemMsg = new SystemMsg(null, systemMsgForm.getTitle(), systemMsgForm.getContext(),
                    new Date(), jwtUser.getUid(), SqlUtils.getIdListString(receivedRids));

            //保存消息
            int i = systemMsgService.addSystemMsg(systemMsg);

            if (i > 0 && systemMsg.getId() != null){
                //获取用户组对应所有UID
                List<User> userList = userService.getUserIdListByRidList(receivedRids);

                List<SystemMsgRead> systemMsgReads = userList.stream().map(user -> {
                    Integer uid = user.getUid();
                    SystemMsgRead systemMsgRead = new SystemMsgRead();
                    systemMsgRead.setMsgId(systemMsg.getId());
                    systemMsgRead.setUid(uid);
                    systemMsgRead.setStatus(Constant.SYSTEM_MSG_READ_STATUS_UNREAD);
                    return systemMsgRead;
                }).collect(Collectors.toList());


                int i1 = systemMsgReadMapper.addSystemReads(systemMsgReads);

                if (i > 0 && i1 > 0){
                    return ResponseUtils.getSuccessResult();
                }
            }



            return ResponseUtils.getFailResult("操作失败");
        }
        return ResponseUtils.getFailResult("请检查token是否有效");
    }
}
