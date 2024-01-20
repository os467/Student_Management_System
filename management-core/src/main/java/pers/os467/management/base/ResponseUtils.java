package pers.os467.management.base;

import pers.os467.management.base.response.PageListResponse;
import pers.os467.management.base.response.ResponseEntity;

import java.util.List;

public class ResponseUtils {

    private static final int RES_CODE_OK = 200;
    private static final int RES_CODE_FAIL = 201;

    public static ResponseEntity getSuccessResult(){
        return getSuccessResult("操作成功",null);
    }

    public static ResponseEntity getSuccessResult(Object obj){
        return getSuccessResult("操作成功",obj);
    }

    public static ResponseEntity getSuccessResult(String msg, Object obj){
        return new ResponseEntity("ok",RES_CODE_OK,msg,obj);
    }


    public static ResponseEntity getFailResult(String msg){
        return getFailResult(msg,null);
    }

    public static ResponseEntity getFailResult(String msg, Object obj){
        return new ResponseEntity("fail",RES_CODE_FAIL,msg,obj);
    }

    //获取分页数据
    public static <T>PageListResponse getPageListResponse(List<T> dataList, Integer currentPage, Integer pageSize,Integer lastPage ,Integer totalCount){
        return new PageListResponse(dataList,currentPage,pageSize,lastPage,totalCount);
    }

}
