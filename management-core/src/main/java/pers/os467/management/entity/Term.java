package pers.os467.management.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("serial")
public class Term {

    private Integer tid;

    private Date termStart;

    private Date termEnd;

}
