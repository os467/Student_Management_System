package pers.os467.management.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("serial")
@Data
public class SystemMsgVo {
    //主键
    private Integer id;
    //消息标题
    private String title;
    //消息内容
    private String context;
    //发布时间
    private String sendTime;
    //发布用户的名称
    private String sendUsername;
    //发布用户id
    private Integer sendUid;
    //状态
    private Integer status;
    //接收用户rids
    private String receiveRid;
}
