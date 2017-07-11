package com.zxh.entity.enums;

/**
 * Created by Administrator on 2017/6/5.
 */
public enum ErrorCodesEnum {
    SUCCESS("成功",0);
    private String msg;
    private int code;

    ErrorCodesEnum(String msg, int code){
        this.msg=msg;
        this.code=code;
    }

    public String getMsg() {
        return msg;
    }

    public int getCode() {
        return code;
    }

}
