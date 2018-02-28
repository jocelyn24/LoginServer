package com.netease.login.entity.response;

/**
 * Created by Jocelyn on 2018/2/28.
 */
public class ResetResult {
    private boolean success;
    private String url;
    private String desc;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

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
