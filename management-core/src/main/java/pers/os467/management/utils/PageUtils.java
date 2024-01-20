package pers.os467.management.utils;

/**
 * 分页工具，需要前端提供当前的页码值，分页单位，需要去数据库查询总数据条目数
 */
public class PageUtils {

    //当前页码 1代表第一页
    private int currentPage;

    //上一页
    private int prePage;

    //下一页
    private int nextPage;

    //最后一页
    private int lastPage;

    //总条目数
    private int totalCount;

    //分页单位
    private int pageSize;

    //数据库查询起始索引位置
    private int startIndex;

    //初始化分页工具
    private void initPageUtils(Integer currentPage, Integer pageSize,Integer totalCount){
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
    }

    public PageUtils(Integer currentPage, Integer pageSize, Integer totalCount){
        initPageUtils(currentPage,pageSize,totalCount);
        initPrePage();
        initLastPage();
        initNextPage();
        initStartIndex();
    }

    private void initStartIndex() {
        this.startIndex = (currentPage - 1) * pageSize;
    }

    private void initNextPage(){
        //若为最后一页则下一页不存在，设置为最后一页
        this.nextPage = currentPage == lastPage ? lastPage : currentPage + 1;
    }

    private void initLastPage() {
        //是否可被分页单位整除
        this.lastPage = totalCount % pageSize == 0? totalCount / pageSize : totalCount / pageSize + 1;
    }

    private void initPrePage() {
        //若为第一页则上一页不存在，设置为第一页
        this.prePage = currentPage == 1 ?1: currentPage - 1;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getPrePage() {
        return prePage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public int getLastPage() {
        return lastPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public int getPageSize() {
        return pageSize;
    }
}
