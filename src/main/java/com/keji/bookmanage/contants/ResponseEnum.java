package com.keji.bookmanage.contants;

/**
 * @auther tangguangchuan
 * @date 2021/1/21 下午4:39
 */
public enum ResponseEnum {
    SUCCESS(0, "success"),
    FAIL(-1, "fail"),
    ERROR_400(400, "错误的请求"),
    ERROR_404(404, "访问资源不存在"),
    ERROR_500(500, "服务器异常");
    private Integer code;
    private String msg;

    ResponseEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
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
}
