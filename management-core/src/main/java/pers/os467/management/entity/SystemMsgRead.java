package pers.os467.management.entity;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * (SystemMsgRead)表实体类
 *
 * @author makejava
 * @since 2023-12-21 20:25:00
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("serial")
public class SystemMsgRead {
    //主键
    private Integer id;
    //用户id
    private Integer uid;
    //消息id
    private Integer msgId;
    //未读0,已读1,删除2
    private Integer status;

}

