package com.mryu.chainnews.entity;

/**
 *@内容 Result请求
 * Created by Administrator on 2018/10/15 0015.
 */

public class Response {
    //错误信息
    private int error_code;
    //成功
    private String reason;
    private Result result;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }
}
