package pers.os467.management.entity;

import java.util.Date;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * (SystemMsg)表实体类
 *
 * @author makejava
 * @since 2023-12-21 19:52:38
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("serial")
public class SystemMsg {
    //主键
    private Integer id;
    //消息标题
    private String title;
    //消息内容
    private String context;
    //发布时间
    private Date sendTime;
    //发布用户id
    private Integer sendUid;
    //接收用户rids
    private String receiveRid;

}

