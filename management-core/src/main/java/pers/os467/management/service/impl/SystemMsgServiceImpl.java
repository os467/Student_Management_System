package pers.os467.management.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.os467.management.base.ResponseUtils;
import pers.os467.management.base.response.ResponseEntity;
import pers.os467.management.common.Constant;
import pers.os467.management.entity.SystemMsg;
import pers.os467.management.entity.SystemMsgRead;
import pers.os467.management.entity.User;
import pers.os467.management.mapper.SystemMsgMapper;
import pers.os467.management.service.SystemMsgReadService;
import pers.os467.management.service.SystemMsgService;
import pers.os467.management.service.UserService;
import pers.os467.management.utils.JwtUser;
import pers.os467.management.utils.SqlUtils;
import pers.os467.management.vo.SystemMsgVo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SystemMsgServiceImpl implements SystemMsgService {

    @Autowired
    private SystemMsgReadService systemMsgReadService;

    @Autowired
    private SystemMsgMapper systemMsgMapper;

    @Autowired
    private UserService userService;

    @Override
    public ResponseEntity getSystemMsgList(JwtUser jwtUser) {
        if (jwtUser.getUid() != null && jwtUser.getRid() != null){
            List<Integer> statusList = new ArrayList<>();
            statusList.add(Constant.SYSTEM_MSG_READ_STATUS_UNREAD);
            statusList.add(Constant.SYSTEM_MSG_READ_STATUS_READ);
            //去查询消息读取表对应用户的消息id列表
            List<SystemMsgRead> systemMsgReadList = systemMsgReadService.getSystemMsgReadList(jwtUser.getUid(),statusList);

            //拿到systemMsg的查询结果集
            List<Integer> msgIdList = SqlUtils.getFieldList(systemMsgReadList, "msgId");

            //查询到符合结果的集合
            Map<Integer,SystemMsg> systemMsgMap = systemMsgMapper.getSystemMsgMap(msgIdList);

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm");

            //聚合数据
            List<SystemMsgVo> systemMsgVoList = systemMsgReadList.stream().map(systemMsgRead -> {
                SystemMsgVo systemMsgVo = new SystemMsgVo();
                SystemMsg systemMsg = systemMsgMap.get(systemMsgRead.getMsgId());
                systemMsgVo.setReceiveRid(systemMsg.getReceiveRid());
                String format = simpleDateFormat.format(systemMsg.getSendTime());
                systemMsgVo.setSendTime(format);
                systemMsgVo.setContext(systemMsg.getContext());
                systemMsgVo.setId(systemMsgRead.getId());
                systemMsgVo.setStatus(systemMsgRead.getStatus());
                systemMsgVo.setTitle(systemMsg.getTitle());
                systemMsgVo.setSendUid(systemMsg.getSendUid());
                return systemMsgVo;
            }).collect(Collectors.toList());


            //拿到外键列表
            List<Integer> ids = SqlUtils.getFieldList(systemMsgVoList, "sendUid");

            //查询结果集合
            Map<Integer, User> usernameMapByIds = userService.getUsernameMapByIds(ids);

            //聚合数据
            for (SystemMsgVo systemMsgVo : systemMsgVoList) {
                User user = usernameMapByIds.get(systemMsgVo.getSendUid());
                systemMsgVo.setSendUsername(user.getUsername());
            }

            return ResponseUtils.getSuccessResult(systemMsgVoList);
        }

        return ResponseUtils.getFailResult("请检查token是否可用");
    }

    @Override
    public int addSystemMsg(SystemMsg systemMsg) {
        return systemMsgMapper.addSystemMsg(systemMsg);
    }
}
