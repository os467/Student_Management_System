package pers.os467.management.base.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class PageListResponse<T> {
    @ApiModelProperty("数据列表")
    private List<T> dataList;
    @ApiModelProperty("当前页码，第一页为1")
    private int currentPage;
    @ApiModelProperty("分页单位")
    private int pageSize;
    @ApiModelProperty("最后一页")
    private int lastPage;
    @ApiModelProperty("总条目数")
    private int totalCount;


    public PageListResponse(List<T> dataList, int currentPage, int pageSize,int lastPage, int totalCount) {
        this.dataList = dataList;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.lastPage = lastPage;
        this.totalCount = totalCount;
    }

}
