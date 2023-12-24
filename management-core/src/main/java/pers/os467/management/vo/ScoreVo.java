package pers.os467.management.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("serial")
public class ScoreVo {
    private String sname;
    private String skId;
    private String cid;
    private String term;
    private String score;
    private String passRate;
}
