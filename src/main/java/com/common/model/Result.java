package com.common.model;


import io.swagger.annotations.ApiModelProperty;


public class Result<T> {

    @ApiModelProperty("状态码")
    private int code = 1;
    /**
     * 数据节点
     */
    @ApiModelProperty("返回数据")
    private T data;
    /**
     * 错误信息
     */
    @ApiModelProperty("信息")
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Result(T data){
        this.data=data;
    }

    public Result(){

    }
    public Result(T data, String msg){
        this.data=data;
        this.msg=msg;
    }
}
