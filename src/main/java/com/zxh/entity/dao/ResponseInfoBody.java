package com.zxh.entity.dao;

import com.zxh.entity.enums.ErrorCodesEnum;

/**
 * Created by Administrator on 2017/6/2.
 */
public class ResponseInfoBody {
    //返回信息
    private String msg;
    //返回错误码
    private Integer errorId;
    private Object data;
    //耗时
    private Long consumeTime;

    public ResponseInfoBody(String msg) {
        this.msg = msg;
        this.errorId = ErrorCodesEnum.SUCCESS.getCode();
        this.consumeTime = 0L;
    }

    public ResponseInfoBody(Object data) {
        this.msg = "";
        this.errorId = ErrorCodesEnum.SUCCESS.getCode();
        this.consumeTime = 0L;
        this.data = data;
    }

    public ResponseInfoBody(String msg, Long consumeTime) {
        this.msg = msg;
        this.errorId = ErrorCodesEnum.SUCCESS.getCode();
        this.consumeTime = consumeTime;
    }


    public Integer getErrorId() {
        return errorId;
    }

    public void setErrorId(Integer errorId) {
        this.errorId = errorId;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Long getConsumeTime() {
        return consumeTime;
    }

    public void setConsumeTime(Long consumeTime) {
        this.consumeTime = consumeTime;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
