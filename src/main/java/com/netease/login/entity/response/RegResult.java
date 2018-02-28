package com.netease.login.entity.response;

/**
 * Created by Jocelyn on 2018/2/28.
 */
public class RegResult {
    private boolean success;
    private boolean exist;
    private String url;
    private String desc;

    public boolean isSuccess() {
        return success;
    }

    public boolean isExist() {return exist;}

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setExist(boolean exist) { this.exist = exist; }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
