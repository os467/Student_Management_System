package pers.os467.management.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("serial")
@Data
public class SystemMsgForm {
    //消息标题
    private String title;
    //消息内容
    private String context;
    //接收用户rids
    private List<Integer> receivedRids;
}
