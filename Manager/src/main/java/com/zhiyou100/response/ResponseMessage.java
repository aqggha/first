package com.zhiyou100.response;

import lombok.Data;

//用来封装响应的
@Data
public class ResponseMessage {
    //响应码
    private int code;
    //相应内容
    private String content;
    //响应是否成功
    private boolean isSuccess;
    //错误原因
    private String error;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
