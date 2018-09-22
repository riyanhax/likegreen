package com.xbdl.xinushop.bean;

public class JsonRootBean {

    /**
     * code : 100
     * msg : 处理成功！
     * extend : {}
     * object : 注册成功
     */

    private int code;
    private String msg;
    private ExtendBean extend;
    private String object;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ExtendBean getExtend() {
        return extend;
    }

    public void setExtend(ExtendBean extend) {
        this.extend = extend;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public static class ExtendBean {
    }
}
