package pers.os467.management.base.response;

import io.swagger.annotations.ApiModelProperty;

/**
 * 统一返回实体
 * @param <T>
 */
public class ResponseEntity<T> {
    @ApiModelProperty("状态:ok 成功,fail 失败")
    private String result;
    @ApiModelProperty("状态码:200成功 201失败")
    private Integer code;
    @ApiModelProperty("消息")
    private String msg;
    @ApiModelProperty("返回的对象")
    private T data;

    public ResponseEntity(String result, Integer code, String msg, T data) {
        this.result = result;
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
