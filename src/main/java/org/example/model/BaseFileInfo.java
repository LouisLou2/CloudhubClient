package org.example.model;

import com.alibaba.fastjson.annotation.JSONField;

public class BaseFileInfo {
    @JSONField(name="userId",ordinal = 0)
    private long userId;
    @JSONField(name="fileId",ordinal = 1)
    private String fileId;
    //constructors
    public BaseFileInfo() {
    }
    public BaseFileInfo(long userId, String fileId) {
        this.userId = userId;
        this.fileId = fileId;
    }
    //setter and getter
    public long getUserId() {
        return userId;
    }
    public void setUserId(long userId) {
        this.userId = userId;
    }
    public String getFileId() {
        return fileId;
    }
    public void setFileId(String fileId) {
        this.fileId = fileId;
    }
    public String toString() {
        return "?"+"userId=" + this.getUserId() + "&fileId=" + this.getFileId();
    }
}
