package com.netease.login.entity.base;

/**
 * Created by Jocelyn on 2018/2/27.
 */
public class BaseResponse<T> {
    private String code;
    private T entity;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getEntity() {
        return entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }
}
