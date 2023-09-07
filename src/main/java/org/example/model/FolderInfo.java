package org.example.model;

import com.alibaba.fastjson.annotation.JSONField;

public class FolderInfo {
    @JSONField(name="folderId")
    private long folderId;
    @JSONField(name="folderName")
    private String folderName;
    @JSONField(name="userId")
    private long userId;
    @JSONField(name="parentFolderId")
    private long parentFolderId;


    public long getFolderId() {
        return folderId;
    }

    public void setFolderId(long folderId) {
        this.folderId = folderId;
    }


    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }


    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getParentFolderId() {
        return parentFolderId;
    }

    public void setParentFolderId(long parentFolderId) {
        this.parentFolderId = parentFolderId;
    }
}
