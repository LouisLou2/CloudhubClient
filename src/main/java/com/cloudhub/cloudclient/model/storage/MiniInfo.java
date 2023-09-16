package com.cloudhub.cloudclient.model.storage;

import com.alibaba.fastjson.annotation.JSONField;

public class MiniInfo {
    @JSONField(name = "id")
    private long id;
    @JSONField(name = "baseType")
    private int baseType;
    public MiniInfo(long id,int baseType){
        this.id=id;
        this.baseType=baseType;
    }
    //setters and getters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getBaseType() {
        return baseType;
    }

    public void setBaseType(int baseType) {
        this.baseType = baseType;
    }
}
